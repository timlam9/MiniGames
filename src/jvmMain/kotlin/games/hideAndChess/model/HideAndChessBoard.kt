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

private const val LEVEL_TEMPLATE =
    "NN11" + "NN12" + "NN13" + "NN14" + "NN15" + "NN16" + "NN17" + "NN18" + "NN19" + "NN1X" +
            "NN21" + "NN22" + "NN23" + "NN24" + "NN25" + "NN26" + "NN27" + "NN28" + "NN29" + "NN2X" +
            "NN31" + "NN32" + "NN33" + "NN34" + "NN35" + "NN36" + "NN37" + "NN38" + "NN39" + "NN3X" +
            "NN41" + "NN42" + "NN43" + "NN44" + "NN45" + "NN46" + "NN47" + "NN48" + "NN49" + "NN4X" +
            "NN51" + "NN52" + "NN53" + "NN54" + "NN55" + "NN56" + "NN57" + "NN58" + "NN59" + "NN5X" +
            "NN61" + "NN62" + "NN63" + "NN64" + "NN65" + "NN66" + "NN67" + "NN68" + "NN69" + "NN6X" +
            "NN71" + "NN72" + "NN73" + "NN74" + "NN75" + "NN76" + "NN77" + "NN78" + "NN79" + "NN7X" +
            "NN81" + "NN82" + "NN83" + "NN84" + "NN85" + "NN86" + "NN87" + "NN88" + "NN89" + "NN8X" +
            "NN91" + "NN92" + "NN93" + "NN94" + "NN95" + "NN96" + "NN97" + "NN98" + "NN99" + "NN9X" +
            "NNX1" + "NNX2" + "NNX3" + "NNX4" + "NNX5" + "NNX6" + "NNX7" + "NNX8" + "NNX9" + "NNXX"

private const val LEVEL_1 =
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

private const val LEVEL_2 =
    "PNN11" + "PNN12" + "PNN13" + "PNN14" + "PNN15" + "OKN16" + "ONN17" + "PNN18" + "PNN19" + "PNN1X" +
            "PNN21" + "PNN22" + "BNN23" + "BKN24" + "PNN25" + "ONN26" + "ONN27" + "PNN28" + "PNN29" + "PNN2X" +
            "PNN31" + "PNN32" + "BNN33" + "BNN34" + "PNN35" + "ONN36" + "ENN37" + "EKN38" + "PNN39" + "PNN3X" +
            "GKN41" + "PNN42" + "BNN43" + "PNN44" + "PNN45" + "PNN46" + "ENN47" + "ENN48" + "PNN49" + "PNN4X" +
            "GNN51" + "GNN52" + "PNN53" + "PNN54" + "PNN55" + "PNN56" + "ENN57" + "PNN58" + "PKN59" + "PNN5X" +
            "KNN61" + "KKN62" + "PNN63" + "PNN64" + "PNN65" + "PNN66" + "PNN67" + "PNN68" + "PNN69" + "PNN6X" +
            "KNN71" + "KNN72" + "CNN73" + "RNN74" + "RKN75" + "YNN76" + "YNN77" + "PNN78" + "PNN79" + "PNN7X" +
            "KNN81" + "CNN82" + "CKN83" + "RNN84" + "RNN85" + "YNN86" + "YNN87" + "PNN88" + "WNN89" + "WNN8X" +
            "PNN91" + "PNN92" + "PNN93" + "RNN94" + "PNN95" + "PNN96" + "YKN97" + "PNN98" + "WNN99" + "WNN9X" +
            "PNNX1" + "PNNX2" + "PNNX3" + "PNNX4" + "PNNX5" + "PNNX6" + "PNNX7" + "PNNX8" + "PNNX9" + "WKNXX"

private const val LEVEL_3 =
    "KNN11" + "KNN12" + "KNN13" + "KNN14" + "KNN15" + "KNN16" + "KKN17" + "KNN18" + "ONN19" + "ONN1X" +
            "KNN21" + "KNN22" + "BNN23" + "ENN24" + "ENN25" + "ENN26" + "ENN27" + "ONN28" + "ONN29" + "GKN2X" +
            "KNN31" + "BKN32" + "BNN33" + "BNN34" + "ENN35" + "ENN36" + "ONN37" + "ONN38" + "GNN39" + "GNN3X" +
            "KNN41" + "KNN42" + "BNN43" + "RNN44" + "EKN45" + "ONN46" + "ONN47" + "YNN48" + "YNN49" + "YNN4X" +
            "KNN51" + "KNN52" + "RNN53" + "RNN54" + "ONN55" + "ONN56" + "CNN57" + "CNN58" + "CKN59" + "YNN5X" +
            "KNN61" + "KNN62" + "RKN63" + "ONN64" + "ONN65" + "CNN66" + "CNN67" + "CNN68" + "CNN69" + "YNN6X" +
            "KNN71" + "KNN72" + "ONN73" + "ONN74" + "YNN75" + "CNN76" + "CNN77" + "PKN78" + "CNN79" + "YNN7X" +
            "KNN81" + "ONN82" + "ONN83" + "YNN84" + "YNN85" + "YKN86" + "PNN87" + "PNN88" + "PNN89" + "YNN8X" +
            "ONN91" + "ONN92" + "WNN93" + "WKN94" + "YNN95" + "YNN96" + "YNN97" + "PNN98" + "YNN99" + "YNN9X" +
            "OKNX1" + "WNNX2" + "WNNX3" + "YNNX4" + "YNNX5" + "YNNX6" + "YNNX7" + "YNNX8" + "YNNX9" + "YNNXX"


val levels = listOf(
    Level(id = 1, board = LEVEL_1),
    Level(id = 2, board = LEVEL_2),
    Level(id = 3, board = LEVEL_3),
)

fun decodeBoard(encodedBoard: String): List<HideAndChessCell> = encodedBoard
    .chunked(HideAndChessCell.ENCODED_CELL_LENGTH)
    .map(HideAndChessCell::decode)
