package es.upm.fi.sos.t3.usermanagement;

import java.rmi.RemoteException;

import es.upm.fi.sos.t3.usermanagement.UserManagementWSStub.*;

public class Cliente {

	public static void main(String[] args) throws RemoteException, InterruptedException {
		
		//stub que usará el superuser
		UserManagementWSStub um = new UserManagementWSStub();
		um._getServiceClient().engageModule("addressing");
		um._getServiceClient().getOptions().setManageSession(true);
		
		//stub que usará user1
		UserManagementWSStub um1 = new UserManagementWSStub();
		um1._getServiceClient().engageModule("addressing");
		um1._getServiceClient().getOptions().setManageSession(true);
		
		//stub que usará user2
		UserManagementWSStub um2 = new UserManagementWSStub();
		um2._getServiceClient().engageModule("addressing");
		um2._getServiceClient().getOptions().setManageSession(true);
		
		
		User superuser = new User();
		superuser.setName("admin");
		superuser.setPwd("admin");

		
		
		User user1 = new User();
		user1.setName("user1");
		user1.setPwd("user1");
		
		Username username1 = new Username();
		username1.setUsername("user1");
		
		PasswordPair ppair1 = new PasswordPair();
		ppair1.setOldpwd("user1");
		ppair1.setNewpwd("asdf");
				
		User user1WP = new User();
		user1WP.setName("user1");
		user1WP.setPwd("wrongPW");
		
		
		
		User user2 = new User();
		user2.setName("user2");
		user2.setPwd("user2");
		
		Username username2 = new Username();
		username2.setUsername("user2");
		
		PasswordPair ppair2 = new PasswordPair();
		ppair2.setOldpwd("user2");
		ppair2.setNewpwd("qwerty");
		
		
		
		User user3 = new User();
		user3.setName("user3");
		user3.setPwd("user3");
		
		Response res;
		
		System.out.println("Ejecutando cliente de prueba...");

		
		System.out.println("Login de superuser");
		res = um.login(superuser);
		System.out.println("\tLa respuesta debe ser true y es " + res.getResponse());
		System.out.println("");
		
		System.out.println("superuser añade al usuario user1");
		res = um.addUser(user1);
		System.out.println("\tLa respuesta debe ser true y es " + res.getResponse());
		System.out.println("");
		
		System.out.println("superuser añade al usuario user1 (ya existe)");
		res = um.addUser(user1);
		System.out.println("\tLa respuesta debe ser false y es " + res.getResponse());
		System.out.println("");
				
	
		System.out.println("superuser intenta borrar un usuario inexistente");
		res = um.removeUser(username2);
		System.out.println("\tLa respuesta debe ser false y es " + res.getResponse());
		System.out.println("");
		
		System.out.println("superuser añade al usuario user2");
		res = um.addUser(user2);
		System.out.println("\tLa respuesta debe ser true y es " + res.getResponse());
		System.out.println("");
		
		
		System.out.println("Login de user1WP (user1 intenta logearse con una contraseña incorrecta)");
		res = um1.login(user1WP);
		System.out.println("\tLa respuesta debe ser false y es " + res.getResponse());
		System.out.println("");

		
		System.out.println("Login de user1 (ya con la contraseña correcta)");
		res = um1.login(user1);
		System.out.println("\tLa respuesta debe ser true y es " + res.getResponse());
		System.out.println("");
		
		
		System.out.println("Intentamos cambiar una contraseña desde un stub con el que aún no hemos hecho login");
		res = um2.changePassword(ppair1);
		System.out.println("\tLa respuesta debe ser false y es " + res.getResponse());
		System.out.println("");
	
		
		System.out.println("Login de user2");
		res = um2.login(user2);
		System.out.println("\tLa respuesta debe ser true y es " + res.getResponse());
		System.out.println("");
		
		
		System.out.println("user1, que no es admin, intenta añadir un nuevo usuario (user3)");
		res = um1.addUser(user3);
		System.out.println("\tLa respuesta debe ser false y es " + res.getResponse());
		System.out.println("");
		
		System.out.println("user1, que no es admin, intenta eliminar un usuario");
		res = um1.removeUser(username2);
		System.out.println("\tLa respuesta debe ser false y es " + res.getResponse());
		System.out.println("");
		
		
		System.out.println("El usuario user1 intenta cambiar su contraseña con un PasswordPair incorrecto");
		res = um1.changePassword(ppair2);
		System.out.println("\tLa respuesta debe ser false y es " + res.getResponse());
		System.out.println("");		
		
		
		System.out.println("El usuario user1 cambia su contraseña a la más segura (?)  \"asdf\"");
		res = um1.changePassword(ppair1);
		System.out.println("\tLa respuesta debe ser true y es " + res.getResponse());
		System.out.println("");

		
		System.out.println("Login de user1 otra vez sin haber hecho logout");
		res = um1.login(user1);
		System.out.println("\tLa respuesta debe ser false y es " + res.getResponse());
		System.out.println("");
		
	
		
		System.out.println("superuser borra el usuario user1");
		res = um.removeUser(username1);
		System.out.println("\tLa respuesta debe ser true y es " + res.getResponse());
		System.out.println("");
		
		
		System.out.println("Logout de superuser");
		um.logout();
		System.out.println("");
		
		
		System.out.println("Logout de user1");
		um1.logout();
		System.out.println("");
		
		System.out.println("Logout de user2");
		um2.logout();
		
	}
}
