package forloooop.speakly.domain.enums

enum class ReportType(val value: Int, var label: String) {
    ABUSE(1, "욕설 및 비방"),
    SPAM(2, "스팸 메시지"),
    ILLEGAL_CONTENT(3, "불법 콘텐츠"),
    PERSONAL_INFO_LEAK(4, "개인 정보 유출"),
    INAPPROPRIATE_BEHAVIOR(5, "부적절한 언행"),
    ETC(6, "부적절한 언행");

    companion object {
        fun fromValue(value: Int): ReportType? {
            return entries.find { it.value == value }
        }
    }
}
