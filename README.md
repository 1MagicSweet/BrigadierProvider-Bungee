# How to use

```java
		new BungeeCommand("testcmd", true) /* Creating an instance of BungeeCommand class which accepts command root name and forced parameter
    if command marked as "not forced" - lambda expression in method "executes(LamCommandExecutor)" will be triggered egardless of user's input */
		.withArguments("foo", "bar") // Arguments for command (primarily for tab-completion)
		.executes((sender, args) -> { // Lambda expression with CommandSender and String[] that will be triggered when user confirmed command execution
			//Your code
		});
```
