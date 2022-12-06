package register;

public class RegisterRun {

	public static void main(String[] args) {
		RegisterFunction regFunc = new RegisterFunction();
		
		EXIT:
		while(true) {
			int choice = regFunc.printMenu();
			
			switch(choice) {
				case 1:
					regFunc.showSubject();
					break;
				case 2:
					regFunc.addSubject();
					break;
				case 3:
					regFunc.showAddSubject();
					break;
				case 4:
					regFunc.exit();
					break EXIT;
				case 9999:	// 관리자만 사용하도록!
					regFunc.checkPeople();
					break;
				default:
					regFunc.exceptionMsg();
					break;
			}
			
		}
	}

}
