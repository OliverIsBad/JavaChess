package Model;

public class ChessPiece {
    private String type; // pawn, rook, knight ...
    private String color; // white or black

    public ChessPiece(String type, String color) {
        this.type = type;
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public String getColor() {
        return color;
    }

    public String getImageFileName() {
        return color + "-" + type + ".png";
    }
}
