import groovy.util.logging.Log4j
import spock.lang.Specification
import spock.lang.Unroll
@Log4j
class ConwaysSpec extends Specification {


    @Unroll
    def "#initialStateOfCell cell with #numberOfNeighbours neighbour(s) #stateOfCell"() {

        when:
        def cellIsAlive = isCellAliveAfterTick(numberOfNeighbours, cellIsInitallyAlive)

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

    def "universe with one alive cell dies in the next generation"() {
        given:
        def cellInitialStructure = [[cellIsInitiallyAlive: true, numberOfNeigbours: 0]]

        when:
        def universeIsAliveAfterTick = isUniverseAliveAfterTick(cellInitialStructure)

        then:
        !universeIsAliveAfterTick

    }

    def "universe with two alive cells that are next to each other dies in the next generation"() {
        given:
        def cellInitialStructure = [[cellIsInitiallyAlive: true, numberOfNeighbours: 1],
                                    [cellIsInitiallyAlive: true, numberOfNeighbours: 1]]

        when:
        def universeIsAliveAfterTick = isUniverseAliveAfterTick(cellInitialStructure)

        then:
        !universeIsAliveAfterTick

    }


    def isUniverseAliveAfterTick(cellInitialStructure) {
        cellInitialStructure.collect {
            isCellAliveAfterTick(it.numberOfNeighbours, it.cellIsInitiallyAlive)
        }.find{it}
    }

    def isCellAliveAfterTick(numberOfNeighbours, cellIsInitiallyAlive) {
        def deadCellWithThreeNeighbours = !cellIsInitiallyAlive && numberOfNeighbours != 3
        def aliveCellWithLessThanTwoOrMoreThanThreeNeighbours = cellIsInitiallyAlive && (numberOfNeighbours < 2 || numberOfNeighbours > 3)
        if (deadCellWithThreeNeighbours || aliveCellWithLessThanTwoOrMoreThanThreeNeighbours)
            return false
        return true
    }

}
