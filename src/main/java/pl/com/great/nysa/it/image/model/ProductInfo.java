package pl.com.great.nysa.it.image.model;

public class ProductInfo {
    private String label;
    private Float score;

    public ProductInfo(String label, Float score) {
        this.label = label;
        this.score = score;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }
}
