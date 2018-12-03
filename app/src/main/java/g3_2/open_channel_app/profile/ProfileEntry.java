package g3_2.open_channel_app.profile;

/**
 * A product entry in the list of products.
 */
public class ProfileEntry {
    private static final String TAG = ProfileEntry.class.getSimpleName();

    public final String firstname;
    public final String lastname;
    public final String dob;
    public final String address;
    public final String postalcode;
    public final String hpnumber;
    public final String homenumber;
    public final String nric;
    public final String email;
    public final String id;
    public final String url;

    public ProfileEntry(
            String firstname, String lastname, String dob, String address, String postalcode, String hpnumber, String homenumber, String nric, String email, String id, String url) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.dob = dob;
        this.address = address;
        this.postalcode = postalcode;
        this.hpnumber = hpnumber;
        this.homenumber = homenumber;
        this.nric = nric;
        this.email = email;
        this.id = id;
        this.url = url;
    }

}