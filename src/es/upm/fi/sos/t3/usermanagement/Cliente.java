package es.upm.fi.sos.t3.usermanagement;

import java.rmi.RemoteException;

import es.upm.fi.sos.t3.usermanagement.UserManagementWSStub.*;

public class Cliente {

	public static void main(String[] args) throws RemoteException, InterruptedException {
		
		UserManagementWSStub um = new UserManagementWSStub();
		um._getServiceClient().engageModule("addressing");
		um._getServiceClient().getOptions().setManageSession(true);
		
		User superuser = new User();
		superuser.setName("admin");
		superuser.setPwd("admin");
		
		User user1 = new User();
		user1.setName("user1");
		user1.setPwd("user1");
		
		Username username1 = new Username();
		username1.setUsername("user1");
		
		PasswordPair ppair = new PasswordPair();
		ppair.setOldpwd("user1");
		ppair.setNewpwd("asdf");
			
		Response res;
		
		System.out.println("Ejecutando cliente de prueba...");
		Thread.sleep(2000);
		
		System.out.println("Login de superuser");
		res = um.login(superuser);
		System.out.println(res.getResponse());
		System.out.println("");
		Thread.sleep(2000);
		
		System.out.println("Añadir el usuario user1");
		res = um.addUser(user1);
		System.out.println(res.getResponse());
		System.out.println("");
		Thread.sleep(2000);
		
		System.out.println("Logout de superuser");
		um.logout();
		System.out.println("");
		Thread.sleep(2000);
		
		System.out.println("Login de user1");
		res = um.login(user1);
		System.out.println(res.getResponse());
		System.out.println("");
		Thread.sleep(2000);
		
		System.out.println("El usuario user1 cambia su contraseña a la más segura (?)  \"asdf\"");
		res = um.changePassword(ppair);
		System.out.println(res.getResponse());
		System.out.println("");
		Thread.sleep(2000);
		
		System.out.println("Logout de user 1");
		um.logout();
		System.out.println("");
		Thread.sleep(2000);
		
		System.out.println("Login de superuser");
		res = um.login(superuser);
		System.out.println(res.getResponse());
		System.out.println("");
		Thread.sleep(2000);
		
		System.out.println("Borrar el usuario user1");
		res = um.removeUser(username1);
		System.out.println(res.getResponse());
		System.out.println("");
		Thread.sleep(2000);
		
		System.out.println("Logout de superuser");
		um.logout();
		
	}
}
