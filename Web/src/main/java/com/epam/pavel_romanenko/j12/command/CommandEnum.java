package com.epam.pavel_romanenko.j12.command;


public enum CommandEnum {

	LOGIN {
		{
			this.command = new LoginCommand();
		}
	},
	LOGOUT {
		{
			this.command = new LogoutCommand();
		}
	},
	MESSAGE {
		{
			this.command = new MessageCommand();
		}
	},
	KICK {
		{
			this.command = new KickCommand();
		}
	},
	UNKICK {
		{
			this.command = new UnkickCommand();
		}
	};
	ActionCommand command;

	public ActionCommand getCurrentCommand() {
		return command;
	}
}
