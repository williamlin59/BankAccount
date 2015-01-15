/**
 * Filename: ErrorChecking.java
 * Description: Controller that is in charge of getting patterns to assist in error checking.
 */

package controller;

/**
 * Class Name: ErrorChecking
 * Description: Controls the error checking operations.
 */
public class ErrorChecking {

	// initialize all the error checking patterns
	private static final String LOGIN_PATTERN ="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,12})";
	private static final String LETTERS_ONLY_PATTERN = "^[a-zA-Z]*$";
	private static final String FIVE_DIGITS_PATTERN="\\d{5}";
	private static final String EMAIL_PATTERN=".*[@].*";
	private static final String TWELVE_DIGITS_PATTERN="\\d{0,12}";

    /**
     * In charge of retrieving the pattern for login error checking.
     * @return LOGIN_PATTERN the pattern for login error checking
     */
	public String getLOGIN_PATTERN(){
		return LOGIN_PATTERN;
	}
	/**
     * In charge of retrieving the pattern for letters only error checking.
     * @return LETTERS_ONLY_PATTERN the pattern for letters only error checking
     */
	public String getLETTERS_ONLY_PATTERN(){
		return LETTERS_ONLY_PATTERN;
	}
	/**
     * In charge of retrieving the pattern for five digits error checking.
     * @return FIVE_DIGITS_PATTERN the pattern for five digits error checking
     */
	public String getFIVE_DIGITS_PATTERN(){
		return FIVE_DIGITS_PATTERN;
	}
	/**
     * In charge of retrieving the pattern for email error checking.
     * @return EMAIL_PATTERN the pattern for email error checking
     */
	public String getEMAIL_PATTERN(){
		return EMAIL_PATTERN;
	}
	/**
     * In charge of retrieving the pattern for fifteen digits error checking.
     * @return FIFTEEN_DIGITS_PATTERN the pattern for fifteen digits error checking
     */
	public String getFIFTEEN_DIGITS_PATTERN(){
		return TWELVE_DIGITS_PATTERN;
	}
	/**
     * In charge of error checking the user's input
     * @return true if the user's input matches the error checking pattern, false otherwise
     */
	public boolean Checking(String userInput,String pattern){
		return userInput.matches(pattern);
	}
	/**
     * In charge of checking the error checking functionality.
     * @param args Not used.
     * @return void
     */
	public static void main(String[] args) {
		ErrorChecking test =new ErrorChecking();
		System.out.println(test.Checking("12345",test.getFIFTEEN_DIGITS_PATTERN()));
		
		
	}
}
