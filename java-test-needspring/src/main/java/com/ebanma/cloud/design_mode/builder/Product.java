package com.ebanma.cloud.design_mode.builder;

public class Product {

    private String background;

    private String icon;

    private String sounds;

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSounds() {
        return sounds;
    }

    public void setSounds(String sounds) {
        this.sounds = sounds;
    }

    @Override
    public String toString() {
        return "Product{" +
                "background='" + background + '\'' +
                ", icon='" + icon + '\'' +
                ", sounds='" + sounds + '\'' +
                '}';
    }

    public static class Builder {

        private Product product;

        public Builder() {
            this.product = new Product();
        }

        public Product build() {
            return product;
        }

        public Builder buildBackground(String background) {
            product.setBackground(background);
            return this;
        }

        public Builder buildIcon(String icon) {
            product.setIcon(icon);
            return this;
        }

        public Builder buildSounds(String sounds) {
            product.setSounds(sounds);
            return this;
        }
    }

}

