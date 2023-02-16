package blogapp.forTests;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Data
public class TravelAndTransportTest {

    @NotBlank(message = "Не должно быть пустым, хоть что-нибудь напиши :)")
    @Pattern(regexp = "[a-zA-Z]*\s*[a-zA-Z]*", message = "\nShould be only words! Such a dumbass")
    private String setOutOff;
    @NotBlank(message = "Не должно быть пустым, хоть что-нибудь напиши :)")
    @Pattern(regexp = "[a-zA-Z]*\s*[a-zA-Z]*", message = "\nShould be only words! Such a dumbass")
    private String checkedIn;
    @NotBlank(message = "Не должно быть пустым, хоть что-нибудь напиши :)")
    @Pattern(regexp = "[a-zA-Z]*\s*[a-zA-Z]*", message = "\nShould be only words! Such a dumbass")
    private String dropMeOff;
    @NotBlank(message = "Не должно быть пустым, хоть что-нибудь напиши :)")
    @Pattern(regexp = "[a-zA-Z]*\s*[a-zA-Z]*", message = "\nShould be only words! Such a dumbass")
    private String turnAround;
    @NotBlank(message = "Не должно быть пустым, хоть что-нибудь напиши :)")
    @Pattern(regexp = "[a-zA-Z]*\s*[a-zA-Z]*", message = "\nShould be only words! Such a dumbass")
    private String takesOff;
    @NotBlank(message = "Не должно быть пустым, хоть что-нибудь напиши :)")
    @Pattern(regexp = "[a-zA-Z]*\s*[a-zA-Z]*", message = "\nShould be only words! Such a dumbass")
    private String runOver;
    @NotBlank(message = "Не должно быть пустым, хоть что-нибудь напиши :)")
    @Pattern(regexp = "[a-zA-Z]*\s*[a-zA-Z]*", message = "\nShould be only words! Such a dumbass")
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
