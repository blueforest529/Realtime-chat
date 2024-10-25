package forloooop.speakly.domain.enums

enum class ReportStatus(val value: Int, var label: String) {
    PENDING(0, "PENDING"),
    COMPLETE(1, "COMPLETE"),
}
