package ui.navigation

private const val MEMO_INFO =
    "Για να παιχτεί αυτό το παιχνίδι χρειάζεται να δημιουργηθεί ο φάκελος με όνομα 'memo' μέσα στον 'downloads' φάκελο του υπολογιστή. Ο φάκελος αυτός πρέπει να περιέχει 12 εικόνες με όνομα 'image1.png', 'image2.jpg' κλπ μέχρι και την εικόνα 12. \n\nΤα είδη των εικόνων που υποστηρίζονται είναι 'jpg' και 'png'. \n\nΣκοπός του παιχνιδιού είναι να βρεθούν τα 12 ζευγάρια των εικόνων."
private const val DEAL_INFO =
    "Για να παίξεις το Deal πρέπει πρώτα να φτιάξεις τις δύο λίστες με τα βραβεία του παιχνιδιού. Η πρώτη λίστα έχει τα λιγότερο καλά βραβεία (Μπλε λίστα) και η δεύτερη λίστα έχει τα καλύτερα βραβεία (Κόκκινη λίστα). Για να φτιάξεις τις λίστες πρέπει να προσθέσεις ένα ένα τα βραβεία επιλέγοντας κάθε φορά σε ποια από τις 2 λίστες θες να το προσθέσεις."
private const val HANGMAN_INFO = "Σκοπός του παιχνιδιού είναι να να βρεις την κρυμμένη λέξη πριν καείς. Έχεις 8 προσπάθειες. Μπορείς να βάλεις είτε αγγλικές είτε ελληνικές λέξεις (χωρίς τόνους)."
private const val HIDE_AND_CHESS_INFO =
    "Ο στόχος είναι να έχεις ακριβώς ένα κομμάτι σκακιού σε κάθε γραμμή, κάθε στήλη και κάθε περιοχή χρώματος. Δύο κομμάτια δεν μπορούν να ακουμπάνε μεταξύ τους, ούτε διαγώνια. \n\n Πάτησε μία φορά για να τοποθετήσεις ένα 'Χ' και δύο φορές για να τοποθετήσεις ένα ΄✌\uFE0F'. Χρησιμοποίησε το 'Χ' για να μαρκάρεις τα σημεία που πιστεύεις ότι δεν μπορεί να μπει ένα κομμάτι σκακιού και το '✌\uFE0F' όπου πιστεύεις ότι μπορεί να μπει."
private const val FIND_THE_STAR_INFO =
    "Σκοπούς του παιχνιδιού είναι να βρεις τον ήλιο-αστέρι. Σε κάποια τετραγωνάκια υπάρχουν τα διαστημόπλοια όπου όλα κοιτάζουν στην στήλη ή την γραμμή που είναι τοποθετημένος ο ήλιος και σε βοηθάνε να τον βρεις. Σε κάποια άλλα τετραγωνάκια υπάρχουν άλλοι πλανήτες (8 πλανήτες και η Σελήνη) και κάποια δεν έχουν τίποτα. Μπορείς να ρυθμίσεις την δυσκολία του παιχνιδιού (πόσα διαστημόπλοια θα υπάρχουν) με τον 'level slider'."
private const val BATTLESHIP_INFO =
    "Σκοπός του παιχνιδιού είναι να πετύχεις και να βουλιάξεις όλα τα πλοία. Τα πλοία είναι 5: 1.Carrier (5 κουτάκια), 2.Battleship (4 κουτάκια) 3.Destroyer (3 κουτάκια) 2. Submarine (3 κουτάκια) και Patrol boat (2 κουτάκια)."
private const val MASTERMIND_INFO =
    "Σκοπός του παιχνιδιού είναι να βρεις την σωστή σειρά των χρωμάτων. Κάθε χρώμα μπορεί να μπει μία μονάχα φορά. Όταν συμπληρωθούν και οι 4 θέσεις χρωμάτων σε κάθε γραμμή τότε δεξιά θα εμφανιστούν και οι απαντήσεις. Το μαύρο σημαίνει ότι έχεις πετύχει το σωστό χρώμα και το άσπρο την σωστή θέση."
private const val KIM_INFO = "Σκοπός του παιχνιδιού είναι να θυμάσε όλα τα αντικείμενα και να τα αναφέρεις όταν κρυφτούν από την οθόνη."

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

    data class Hangman(
        val title: String = "Hangman info",
        val content: String = HANGMAN_INFO,
        val image: String = "home/hangman.jpeg",
    ) : GameInfoScreen

    data class HideAndChess(
        val title: String = "Hide and Chess info",
        val content: String = HIDE_AND_CHESS_INFO,
        val image: String = "home/hide_and_chess.png",
    ) : GameInfoScreen

    data class FindTheStar(
        val title: String = "Find the star info",
        val content: String = FIND_THE_STAR_INFO,
        val image: String = "home/find_the_star.png",
    ) : GameInfoScreen

    data class Battleship(
        val title: String = "Battleship info",
        val content: String = BATTLESHIP_INFO,
        val image: String = "home/battleship_menu.png",
    ) : GameInfoScreen

    data class Mastermind(
        val title: String = "Mastermind info",
        val content: String = MASTERMIND_INFO,
        val image: String = "home/mastermind.png",
    ) : GameInfoScreen

    data class KimGame(
        val title: String = "Kim's game info",
        val content: String = KIM_INFO,
        val image: String = "home/kim_game.png",
    ) : GameInfoScreen
}
