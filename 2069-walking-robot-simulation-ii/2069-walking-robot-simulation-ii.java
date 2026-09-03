class Robot {
    private int w;
    private int h;
    private int perimeter;
    private int pos;
    private boolean hasMoved;

    public Robot(int width, int height) {
        this.w = width;
        this.h = height;
        // Total steps to complete exactly one full lap around the grid
        this.perimeter = 2 * (w - 1) + 2 * (h - 1);
        this.pos = 0;
        this.hasMoved = false;
    }
    
    public void step(int num) {
        // Use modulo to avoid simulating unnecessary full laps
        pos = (pos + num) % perimeter;
        hasMoved = true;
    }
    
    public int[] getPos() {
        int p = pos;
        if (p == 0) return new int[]{0, 0};
        
        // Bottom edge
        if (p < w) return new int[]{p, 0};
        p -= (w - 1);
        
        // Right edge
        if (p < h) return new int[]{w - 1, p};
        p -= (h - 1);
        
        // Top edge
        if (p < w) return new int[]{w - 1 - p, h - 1};
        p -= (w - 1);
        
        // Left edge
        return new int[]{0, h - 1 - p};
    }
    
    public String getDir() {
        // Edge case: Origin point direction depends on if the robot has ever moved
        if (pos == 0) {
            return hasMoved ? "South" : "East";
        }
        
        // Direction based on which edge of the perimeter the robot is currently on
        if (pos < w) return "East";
        if (pos < w - 1 + h) return "North";
        if (pos < 2 * w - 2 + h) return "West";
        
        return "South";
    }
}

/**
 * Your Robot object will be instantiated and called as such:
 * Robot obj = new Robot(width, height);
 * obj.step(num);
 * int[] param_2 = obj.getPos();
 * String param_3 = obj.getDir();
 */