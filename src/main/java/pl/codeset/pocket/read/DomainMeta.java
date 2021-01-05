package pl.codeset.pocket.read;

import java.util.Objects;

public class DomainMeta {
    private final String name;
    private final String logo;
    private final String greyscaleLogo;

    public DomainMeta(String name, String logo, String greyscaleLogo) {
        this.name = name;
        this.logo = logo;
        this.greyscaleLogo = greyscaleLogo;
    }

    public String getGreyscaleLogo() {
        return greyscaleLogo;
    }

    public String getLogo() {
        return logo;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "DomainMeta{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomainMeta that = (DomainMeta) o;
        return Objects.equals(name, that.name) && Objects.equals(logo, that.logo) && Objects.equals(greyscaleLogo, that.greyscaleLogo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, logo, greyscaleLogo);
    }
}
