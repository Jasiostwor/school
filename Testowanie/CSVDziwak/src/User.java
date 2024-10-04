public class User {
    private String imie;
    private String nazwisko;
    private String dlugosc;
    private String status;

    public User(String firstName, String lastName, String courseDuration) {
        this.imie = firstName;
        this.nazwisko = lastName;
        this.dlugosc = courseDuration;
        this.status = checkCertificateStatus() ? "Tak" : "Nie";
    }

    public String getNazwisko(){
        return nazwisko;
    }

    private boolean checkCertificateStatus(){
        String[] parts = this.dlugosc.replace("\0", "").split(" ");
        return parts.length == 6;
    }

    public String getImie() {
        return imie;
    }

    public String getDlugosc() {
        return dlugosc;
    }

    public String getStatus() {
        return status;
    }
}