package games.hideAndChess.model

data class HideAndChessBoard(
    val value: List<HideAndChessCell>
)

/**
 * 1 to 10
 */
val BoardCoordinates = List(10) {
    it + 1
}

const val InitialEncodedBoard =
    "PNN11" + "PNN12" + "PNN13" + "PNN14" + "PNN15" + "ONN16" + "BKN17" + "BNN18" + "BNN19" + "BNN1X" +
            "PNN21" + "ENN22" + "ONN23" + "ONN24" + "OKN25" + "ONN26" + "GNN27" + "GNN28" + "GNN29" + "BNN2X" +
            "PNN31" + "EKN32" + "ONN33" + "KNN34" + "KNN35" + "GNN36" + "GNN37" + "GNN38" + "GNN39" + "BNN3X" +
            "PNN41" + "ENN42" + "KNN43" + "KKN44" + "GNN45" + "GNN46" + "GNN47" + "GNN48" + "GNN49" + "BNN4X" +
            "PKN51" + "KNN52" + "KNN53" + "GNN54" + "GNN55" + "GNN56" + "GNN57" + "GNN58" + "CNN59" + "CNN5X" +
            "KNN61" + "KNN62" + "GNN63" + "GNN64" + "GNN65" + "GNN66" + "GNN67" + "KNN68" + "CKN69" + "CNN6X" +
            "KNN71" + "GNN72" + "GKN73" + "GNN74" + "GNN75" + "GNN76" + "KNN77" + "KNN78" + "RNN79" + "CNN7X" +
            "KNN81" + "GNN82" + "GNN83" + "GNN84" + "GNN85" + "KNN86" + "KNN87" + "RKN88" + "RNN89" + "CNN8X" +
            "KNN91" + "GNN92" + "GNN93" + "GNN94" + "KNN95" + "KNN96" + "RNN97" + "RNN98" + "YNN99" + "YKN9X" +
            "KNNX1" + "KNNX2" + "KNNX3" + "KNNX4" + "KNNX5" + "WKNX6" + "WNNX7" + "WNNX8" + "YNNX9" + "YNNXX"

fun decodeBoard(encodedBoard: String): List<HideAndChessCell> = encodedBoard
    .chunked(HideAndChessCell.ENCODED_CELL_LENGTH)
    .map(HideAndChessCell::decode)
