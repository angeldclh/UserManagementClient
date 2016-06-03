package es.upm.fi.sos.t3.usermanagement;

import java.rmi.RemoteException;

import es.upm.fi.sos.t3.usermanagement.UserManagementWSStub.*;

public class Cliente {

	public static void main(String[] args) throws RemoteException {
		
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
			
		
		System.out.println("Ejecutando cliente. La lista actual de usuarios es "
		+ um.verUsuarios());
		
		System.out.println("Login de superuser");
		um.login(superuser);
		
		
		System.out.println("Añadir el usuario user1");
		um.addUser(user1);
		
		System.out.println("Logout de superuser");
		um.logout();
		
		System.out.println("Login de user1");
		um.login(user1);
		
		System.out.println("El usuario user1 cambia su contraseña a la más segura asdf");
		um.changePassword(ppair);
		
		
		System.out.println("Logout de user 1");
		um.logout();
		
		System.out.println("Login de superuser");
		um.login(superuser);
		
		System.out.println("Borrar el usuario user1");
		um.removeUser(username1);
		
		
		System.out.println("Logout de superuser");
		um.logout();
		
	}
}
