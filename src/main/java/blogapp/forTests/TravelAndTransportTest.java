package blogapp.forTests;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class TravelAndTransportTest {

    @Pattern(regexp = "[a-zA-Z]*", message = "\nShould be only words! Such a dumbass")
    private String setOutOff;
    @Pattern(regexp = "[a-zA-Z]*", message = "\nShould be only words! Such a dumbass")
    private String checkedIn;
    @Pattern(regexp = "[a-zA-Z]*", message = "\nShould be only words! Such a dumbass")
    private String dropMeOff;
    @Pattern(regexp = "[a-zA-Z]*", message = "\nShould be only words! Such a dumbass")
    private String turnAround;
    @Pattern(regexp = "[a-zA-Z]*", message = "\nShould be only words! Such a dumbass")
    private String takesOff;
    @Pattern(regexp = "[a-zA-Z]*", message = "\nShould be only words! Such a dumbass")
    private String runOver;
    @Pattern(regexp = "[a-zA-Z]*", message = "\nShould be only words! Such a dumbass")
    private String keepUpWith;

    @Override
    public String toString() {
        return "TravelAndTransportTest{" +
                "setOutOff='" + setOutOff + '\'' +
                ", checkedIn='" + checkedIn + '\'' +
                ", dropMeOff='" + dropMeOff + '\'' +
                ", turnAround='" + turnAround + '\'' +
                ", takesOff='" + takesOff + '\'' +
                ", runOver='" + runOver + '\'' +
                ", keepUpWith='" + keepUpWith + '\'' +
                '}';
    }
}
