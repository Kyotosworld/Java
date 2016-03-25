public class Complexe2 extends Complexe {

    public Complexe2(double x, double y, boolean cartesien) {
        if (cartesien) {
            this.Re = x;
            this.Im = y;
            this.setPolaires();
        } else {
            this.Mod = x;
            this.Arg = y;
            this.setCartesiennes();
        }
    }

    public String toString() {
        return "z = "+ this.Re +" "+ this.Im +"i  =  "+ this.Mod +"*exp("+ this.Arg +"i)";
    }
}
