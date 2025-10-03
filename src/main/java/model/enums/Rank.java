package model.enums;

public enum Rank {
    // Aikido ranks
    KYU_6(6), KYU_5(5), KYU_4(4), KYU_3(3), KYU_2(2), KYU_1(1),
    DAN_1(101), DAN_2(102), DAN_3(103), DAN_4(104), DAN_5(105),
    DAN_6(106), DAN_7(107), DAN_8(108), DAN_9(109), DAN_10(110);

    public final int level;
    Rank(int level) {
        this.level = level;
    }
    public static Rank fromLevel(Integer level) {
        if (level == null) {return null;}
        for (Rank rank : values()) {
            if (rank.level == level) {return rank;}
        }
        throw new IllegalArgumentException("Invalid rank level: " + level);
    }
}
