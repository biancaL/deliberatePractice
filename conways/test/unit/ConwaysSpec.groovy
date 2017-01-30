import spock.lang.Specification
import spock.lang.Unroll

class ConwaysSpec extends Specification {


    @Unroll
    def "#initialStateOfCell cell with #numberOfNeighbours neighbour(s) #stateOfCell"() {

        when:
        def cellIsAlive = tick(numberOfNeighbours, cellIsInitallyAlive)

        then:
        cellIsAliveAfterTick == cellIsAlive

        where:
        cellIsInitallyAlive | numberOfNeighbours | cellIsAliveAfterTick
        true                | 0                  | false
        true                | 1                  | false
        true                | 2                  | true
        true                | 3                  | true
        true                | 4                  | false
        false               | 0                  | false
        false               | 1                  | false
        false               | 2                  | false
        false               | 3                  | true
        false               | 4                  | false

        stateOfCell = cellIsAliveAfterTick ? "is alive in the next generation" : "is dead in the next generation"
        initialStateOfCell = cellIsInitallyAlive ? "alive" : "dead"

    }


    def tick(numberOfNeighbours, cellIsInitiallyAlive) {
        def deadCellWithThreeNeighbours = !cellIsInitiallyAlive && numberOfNeighbours != 3
        def aliveCellWithLessThanTwoOrMoreThanThreeNeighbours = cellIsInitiallyAlive && (numberOfNeighbours < 2 || numberOfNeighbours > 3)
        if (deadCellWithThreeNeighbours || aliveCellWithLessThanTwoOrMoreThanThreeNeighbours)
            return false
        return true
    }

    def "one alive cell in the universe dies"() {
        given:
        def initialUniverse = [[0, 0, 0, 0],
                               [0, 1, 0, 0],
                               [0, 0, 0, 0],
                               [0, 0, 0, 0]]
        when:
            def universe = theWholeUniverseIsTicking(initialUniverse)
        then:
        universe == emptyUniverse()
    }

    def theWholeUniverseIsTicking(def initialUniverse) {
        return emptyUniverse()
    }

    def emptyUniverse() {
        return [[0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0]]
    }
}
