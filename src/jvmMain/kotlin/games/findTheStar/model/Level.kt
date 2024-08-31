package games.findTheStar.model

enum class Level(val rockets: Int) {

    TOO_EASY(rockets = 80),
    EASY(rockets = 60),
    MEDIUM(rockets = 40),
    HARD(rockets = 20),
    TOO_HARD(rockets = 10),
}
