package Controller;

import Action.Action;
import Action.DeletePostAction;
import Action.JoinAction;
import Action.JoinFormAction;
import Action.LoginAction;
import Action.LoginFormAction;
import Action.ModifyInfoAction;
import Action.ModifyInfoFormAction;
import Action.NewThreadAction;
import Action.PasswordCheckAction;
import Action.PostWriteAction;
import Action.WallPostsAction;

public class ActionFactory {
	private static ActionFactory instance = new ActionFactory();

	private ActionFactory() {
		super();
	}

	public static ActionFactory getInstance() {
		return instance;
	}

	public Action getAction(String command) {
		Action action = null;
		System.out.println("ActionFactory :" + command);
		if (command.equals("loginPage")||command.equals("logOut"))
			action = new LoginFormAction();
		else if (command.equals("login"))
			action = new LoginAction();
		else if (command.equals("joinPage"))
			action = new JoinFormAction();
		else if (command.equals("join"))
			action = new JoinAction();
		else if (command.equals("wallPosts"))
			action = new WallPostsAction();
		else if (command.equals("postWrite"))
			action = new PostWriteAction();
//		else if (command.equals("passwordCheckForm"))
//			action = new PasswordCheckFormAction();
		else if (command.equals("passwordCheck"))
			action = new PasswordCheckAction();
		else if (command.equals("modifyInfoForm"))
			action = new ModifyInfoFormAction();
		else if (command.equals("modifyInfo"))
			action = new ModifyInfoAction();
		else if (command.equals("newThread"))
			action = new NewThreadAction();
		else if (command.equals("deletePost"))
			action = new DeletePostAction();
		
		return action;
	}
}
