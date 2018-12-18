package utilities;

public class Enums {

    public enum HREnum {
        SINGLE_SKILL,
        MULTI_SKILL,
        UP_SKILL
    }

    public enum HRMultiSkillEnum {
        TRAIN,
        HIRE
    }

    public enum HRUPSkillEnum {
        TRAIN,
        HIRE
    }

    public enum Context_Type {
        PRODUCTION_LINE,
        NO_PRODUCTION_LINE
    }

    public enum ProductionLine_Type {
        PERMUTATION,
        NO_PERMUTATION
    }

    public enum Scheduling_Type {
        SCHEDULING_OFF_SITE,
        SCHEDULING_NO_PRODUCTION_LINE
    }
}
