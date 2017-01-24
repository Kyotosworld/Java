class Decorateur {

    public static void main(String[] args) {
        Decorateur generateur = new Decorateur();
        Decorateur.Gateau g = new Decorateur.Gateau(
                                new Decorateur.Chocolat(
                                    new Decorateur.CremeVanille(
                                        new Decorateur.Chantilly(
                                            new Decorateur.CoulisDeFruitsRouges()))));

        System.out.println("Objet gateau: "+g);
    }

    static class Patisserie {
        String description = "";
        Patisserie() {}
        Patisserie(Patisserie p) {
            description += p.toString();
        }
        public String toString() {
            return description;
        }
    }
    static class Gateau extends Patisserie {
        Gateau(Patisserie p) {
            super(p);
            description = "Et une couche de Gateau\n" + description;
        }
        Gateau() {
            description = "Et une couche de Gateau\n";
        }

    }
    static class Chocolat extends Patisserie {
        Chocolat(Patisserie p) {
            super(p);
            description = "Et une couche de Chocolat\n" + description;
        }
        Chocolat() {
            description = "Et une couche de Chocolat\n";
        }

    }
    static class CremeVanille extends Patisserie {
        CremeVanille(Patisserie p) {
            super(p);
            description = "Et une couche de CremeVanille\n" + description;
        }
        CremeVanille() {
            description = "Et une couche de CremeVanille\n";
        }

    }
    static class Chantilly extends Patisserie {
        Chantilly(Patisserie p) {
            super(p);
            description = "Et une couche de Chantilly\n" + description;
        }
        Chantilly() {
            description = "Et une couche de Chantilly\n";
        }
    }
    static class CoulisDeFruitsRouges extends Patisserie {
        CoulisDeFruitsRouges(Patisserie p) {
            super(p);
            description = "Et une couche de CoulisDeFruitsRouges\n" + description;
        }
        CoulisDeFruitsRouges() {
            description = "Et une couche de CoulisDeFruitsRouges\n";
        }
    }
}