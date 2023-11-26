package teamcode.Susbsystems;

public final class ArmConstants {

    public static final class PMC {
        public static int HOME = 0;
        public static int PICKUP_IN = -1820;
        public static int PICKUP_OUT = -3000;
        public static int SCORE = 2350;

        public static int HANG_UP = 100;
        public static int HANG_DOWN = 100;
    }

    public static final class EMC {
        public static int HOME = 0;
        public static int PICKUP_IN = 0;
        public static int PICKUP_OUT = -5200;
        public static int SCORE = -10400;

        public static int HANG_UP = 100;
        public static int HANG_DOWN = 100;
    }

    public static final class PSC {
        public static double HOME = 0.3;
        public static double PICKUP_IN = 0.33;
        public static double PICKUP_OUT = 0.5;
        public static double SCORE = 1.0;
    }

    public static final class GSC {
        public static double GRAB = 0.05;
        public static double RELEASE = 0.2;

    }

}