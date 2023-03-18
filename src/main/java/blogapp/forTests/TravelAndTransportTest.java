package blogapp.forTests;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Data
public class TravelAndTransportTest {
    public static final String str = "Не должно быть пустым, хоть что-нибудь напиши :)";
    public static final String message = "\nShould be only words! Such a dumbass";
    public static final String regexp = "[a-zA-Z]*\s*[a-zA-Z]*";

    @NotBlank(message = str)
    @Pattern(regexp = regexp, message = message)
    private String setOutOff;
    @NotBlank(message = str)
    @Pattern(regexp = regexp, message = message)
    private String checkedIn;
    @NotBlank(message = str)
    @Pattern(regexp = regexp, message = message)
    private String dropMeOff;
    @NotBlank(message = str)
    @Pattern(regexp = regexp, message = message)
    private String turnAround;
    @NotBlank(message = str)
    @Pattern(regexp = regexp, message = message)
    private String takesOff;
    @NotBlank(message = str)
    @Pattern(regexp = regexp, message = message)
    private String runOver;
    @NotBlank(message = str)
    @Pattern(regexp = regexp, message = message)
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
