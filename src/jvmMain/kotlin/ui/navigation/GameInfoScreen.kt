package ui.navigation

private const val MEMO_INFO =
    "Για να παιχτεί αυτό το παιχνίδι χρειάζεται να δημιουργηθεί ο φάκελος με όνομα 'memo' μέσα στον 'downloads' φάκελο του υπολογιστή. Ο φάκελος αυτός πρέπει να περιέχει 12 εικόνες με όνομα 'image1.png', 'image2.jpg' κλπ μέχρι και την εικόνα 12. \n\nΤα είδη των εικόνων που υποστηρίζονται είναι 'jpg' και 'png'. \n\nΣκοπός του παιχνιδιού είναι να βρεθούν τα 12 ζευγάρια των εικόνων."
private const val DEAL_INFO = "Για να παίξεις το Deal πρέπει πρώτα να φτιάξεις τις δύο λίστες με τα βραβεία του παιχνιδιού. Η πρώτη λίστα έχει τα λιγότερο καλά βραβεία (Μπλε λίστα) και η δεύτερη λίστα έχει τα καλύτερα βραβεία (Κόκκινη λίστα). Για να φτιάξεις τις λίστες πρέπει να προσθέσεις ένα ένα τα βραβεία επιλέγοντας κάθε φορά σε ποια από τις 2 λίστες θες να το προσθέσεις."

sealed interface GameInfoScreen {

    object None : GameInfoScreen
    data class Memo(
        val title: String = "Memo info",
        val content: String = MEMO_INFO,
        val image: String = "home/ic_memo.png",
    ) : GameInfoScreen

    data class Deal(
        val title: String = "Deal info",
        val content: String = DEAL_INFO,
        val image: String = "home/deal.jpeg",
    ) : GameInfoScreen
}
