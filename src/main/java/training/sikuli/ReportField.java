package training.sikuli;

enum ReportField {
    TERRITORY("dojoUnique2"), COUNTRY("dojoUnique3"), SALES("dojoUnique7");

    private final String domId;

    ReportField(String domId) {
        this.domId = domId;
    }

    String getDomId() {
        return domId;
    }
}
