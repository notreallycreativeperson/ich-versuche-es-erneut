class Main {

    static final int COLUMNS=7;
    static final int ROWS=6;
    static final int[][] INDICES={
            {1, 0},
            {1, 1},
            {0, 1},
            {-1, 1}
    };
    public static final int MAX_MOVES = COLUMNS * ROWS;
    public static final int WINNING_LENGTH = 4;
    static final boolean[][][] directions = new boolean[COLUMNS][ROWS][INDICES.length];


    public static void main(String[] args) {
        setDirections();
    }

    private static void setDirections() {
        // Only initialize if not already set
        if (directions[0][0][0]) return;
        for (int i = 0; i < COLUMNS; i++) {
            for (int j = 0; j < ROWS; j++) {
                directions[i][j][0] = i < COLUMNS - WINNING_LENGTH + 1;
                directions[i][j][1] = (i < COLUMNS - WINNING_LENGTH + 1 && j < ROWS - WINNING_LENGTH + 1);
                directions[i][j][2] = j < ROWS - WINNING_LENGTH + 1;
                directions[i][j][3] = (i > WINNING_LENGTH - 2 && j < ROWS - WINNING_LENGTH + 1);
            }
        }
    }
}