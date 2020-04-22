package es.urjccode.mastercloudapps.adcs.draughts.utils;

public class YesNoDialog  {

	private static final char AFIRMATIVE = 'y';
	private static final char NEGATIVE = 'n';
	private static final String QUESTION = "? ("+YesNoDialog.AFIRMATIVE+"/"+YesNoDialog.NEGATIVE+"): ";
	private static final String ERROR = "The value must be '" + YesNoDialog.AFIRMATIVE + "' or '"
			+ YesNoDialog.NEGATIVE + "'";
    private Console console;

    public YesNoDialog(){
        this.console = new Console();
    }
    
	public boolean read(String title) {
		assert title != null;
		char answer;
		boolean ok;
		do {
			answer = this.console.readChar(title + YesNoDialog.QUESTION);
			ok = YesNoDialog.isAfirmative(answer) || YesNoDialog.isNegative(answer);
			if (!ok) {
				this.console.writeln(YesNoDialog.ERROR);
			}
		} while (!ok);
		return YesNoDialog.isAfirmative(answer);
	}

	private static boolean isAfirmative(char answer) {
		return Character.toLowerCase(answer) == YesNoDialog.AFIRMATIVE;
	}

	private static boolean isNegative(char answer) {
		return Character.toLowerCase(answer) == YesNoDialog.NEGATIVE;
	}

}