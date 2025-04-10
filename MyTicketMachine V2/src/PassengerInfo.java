public class PassengerInfo {
    private final String passengerName;
    private String phoneNumber;
    private String emailAddress;

    public PassengerInfo(String passengerName, String phoneNumber, String emailAddress) {
        this.passengerName = passengerName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "PassengerInfo[ " + "UserID: " + passengerName + ", Phone Number: " + phoneNumber + ", Email Address: " + emailAddress + "]";
    }
}
