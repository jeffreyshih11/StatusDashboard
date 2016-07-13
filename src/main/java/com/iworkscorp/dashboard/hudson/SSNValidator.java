package com.iworkscorp.dashboard.hudson;

public class SSNValidator {

    //private static final Logger _log = LoggerFactory.getLogger(SSNValidator.class);
    private String validationErrorSSN = "";
    String REGEX_ALL_DIGITS = "\\d+";
    String REGEX_ALL_ALPHA = "^[A-Z]+$";
    private boolean isPseudo = false;


    /*
     * THE FOLLOWING TEXT IS THE SOCIAL SECURITY NUMBER GUIDELINES PUBLISHED ON SSA WEBSITE
     * =======================================================================================================
     * Which Social Security numbers are invalid?
     * An invalid Social Security number is one that we never assigned.
     * On June 25, 2011, we began assigning Social Security numbers differently than in the past.
     * Today, Social Security numbers are assigned through a randomized process.
     * However, there are some 9 digit numbers that we do not include in our randomized process.
     * We do not assign a Social Security number with the first three digits of:
     *	 000
     *	 666
     *	 900 - 999
     * Nor do we assign Social Security number with "00" in the fourth and fifth position,
     * or "0000" in the last four positions of the numbers.
     *
     * Additionally, prior to June 25, 2011, we had never assigned a Social Security number with
     * the first three digits of:
     *	 000
     *	 666
     *	 772 - 799
     *	 800 - 899
     *	 900 - 999
     * Employers can use the Social Security Number Verification Service to verify the names
     * and Social Security numbers of former and current employees.
     */

    public boolean isSSNValid(String ssn) {
        isPseudo = false;
        setSSNValidationError("");
        if (!meetsBasicValidation(ssn)) {
            return false;
        }

        if (isPseudo(ssn)) {
            isPseudo = true;
            boolean validPseudoSSN = this.isValidPseudoSSN(ssn);
            if (validPseudoSSN) return true;
            setSSNValidationError("Pseudo SSN is not valid");
            return false;
        }

        if (!ssn.toUpperCase().matches("[0-9]+")) {
            setSSNValidationError("Social Security Number " + this.formatWithDashes(ssn) + " is not all numeric");
            return false;
        }

        String firstThree = ssn.substring(0, 3);
        if (firstThree.equals("000") || ssn.substring(3, 5).equals("00") || ssn.substring(5, 9).equals("0000")) {
            setSSNValidationError("Social Security Number " + this.formatWithDashes(ssn) + " cannot have a section that is all zeroes");
            return false;
        }

        Integer issn = Integer.parseInt(firstThree);
        if (firstThree.equals("666") || (issn >= 900 && issn <= 999)) {
            setSSNValidationError("Social Security Number '" + this.formatWithDashes(ssn) +
                    "' begin with invalid numbers (666 and 900-999 are invalid)");
            return false;
        }

        return true;
    }

    public boolean isSSNValid(boolean isPseudoSSN, String ssn) {
        setSSNValidationError("");
        if (!meetsBasicValidation(ssn)) {
            return false;
        }

        if (isPseudoSSN) {
            return this.isValidPseudoSSN(ssn);
        } else {
            if (!ssn.toUpperCase().matches("[0-9]+")) {
                setSSNValidationError("Social Security Number " + this.formatWithDashes(ssn) + " is not all numeric");
                return false;
            }
            String firstThree = ssn.substring(0, 3);
            if (firstThree.equals("000") || ssn.substring(3, 5).equals("00") || ssn.substring(5, 9).equals("0000")) {
                setSSNValidationError("Social Security Number " + this.formatWithDashes(ssn) + " cannot have a section that is all zeroes");
                return false;
            }
            Integer issn = Integer.parseInt(firstThree);
            if (firstThree.equals("666") || (issn >= 900 && issn <= 999)) {
                setSSNValidationError("Social Security Number " + this.formatWithDashes(ssn) + " cannot begin with the invalid numbers of "
                        + firstThree + " ... (666 and 900-999 are invalid)");
                return false;
            }
            return true;
        }
    }

    private boolean meetsBasicValidation(String ssn) {
        int length = ssn.length();
        if ((length == 0) || ssn.contains("_") || ssn.contains("-")) {
            // one of our forms is pre-populated with underscores,
            // so an all-underscore SSN should be considered the same as a blank ssn
            setSSNValidationError("Social Security Number is invalid");
            return false;
        }
        if (length < 9) {
            setSSNValidationError("Social Security Number " + ssn + " is too short. 9 digits are needed");
            return false;
        }
        if (length > 9) {
            setSSNValidationError("Social Security Number " + ssn + " is too long. Only 9 digits are needed");
            return false;
        }
        return true;
    }

    private String formatWithDashes(String ssn) {
        return ssn.substring(0,3) + "-" + ssn.substring(3,5) + "-" + ssn.substring(5,9);
    }

    private boolean isPseudo(String ssn) {
        if (isAllAlpha(ssn.substring(0, 1)) || isAllAlpha(ssn.substring(3, 4)) || isAllAlpha(ssn.substring(4, 5))) {
            return true;
        } else {
            return ssn.substring(0, 1).matches("9") || ssn.substring(0, 3).matches("DSS");
        }
    }

    private boolean isValidPseudoSSN(String ssn) {
        //  1. AXX-XX-XXXX (for OPM Pseudo SSN)
        //  2. XXX-AX-XXXX (for local nationals)
        //  3. XXX-XA-XXXX (for local nationals)
        //  4. DSS-XX-XXXX (for DSS Foreign Nationals)
        //  5. 9XX-XX-XXXX (for DSS Foreign Nationals)
        //
        // where X is a numeric digit and A is an alpha

        if (ssn.substring(0, 3).matches("DSS")) {
            return this.isAllDigits(ssn.substring(3, 9));
        }
        if (isAllAlpha(ssn.substring(0, 1))) {
            return this.isAllDigits(ssn.substring(1, 9));
        }
        if (isAllAlpha(ssn.substring(3, 4))) {
            return this.isAllDigits(ssn.substring(0, 3)) && this.isAllDigits(ssn.substring(4, 9));
        }
        if (isAllAlpha(ssn.substring(4, 5))) {
            return this.isAllDigits(ssn.substring(0, 4)) && this.isAllDigits(ssn.substring(5, 9));
        }
        return ssn.substring(0, 1).matches("9") && this.isAllDigits(ssn);
    }

    boolean isAllDigits(String string) {
        return string.matches(REGEX_ALL_DIGITS);
    }

    boolean isAllAlpha(String string) {
        return string.matches(REGEX_ALL_ALPHA);
    }

    public String getSSNValidationError() {
        return validationErrorSSN;
    }

    public void setSSNValidationError(String errMsg) {
        //System.out.println("SSNValidationError, if any, is: " + errMsg);
        validationErrorSSN = errMsg;
    }

    public String getValidationErrorSSN() {
        return validationErrorSSN;
    }

    public boolean isPseudo() {
        return isPseudo;
    }
}
