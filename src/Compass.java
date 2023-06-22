enum Compass {
    NORTH(new int[]{1, 0}),
    NORTH_EAST(new int[]{1, 1}),
    EAST(new int[]{0, 1}),
    SOUTH_EAST(new int[]{-1, 1}),
    SOUTH(new int[]{-1, 0}),
    SOUTH_WEST(new int[]{-1, -1}),
    WEST(new int[]{0, -1}),
    NORTH_WEST(new int[]{1, -1});

    private final int[] movement;

    Compass(int[] movement) {
        this.movement = movement;
    }

    int[] getMovement() {
        return this.movement;
    }
}
