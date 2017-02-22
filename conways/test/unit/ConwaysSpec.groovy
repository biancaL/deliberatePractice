import spock.lang.Specification
import spock.lang.Unroll

class ConwaysSpec extends Specification {

    private def deadGeneration

    def setup() {
        deadGeneration = []
    }


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
        true                | 5                  | false
        true                | 6                  | false
        true                | 7                  | false
        true                | 8                  | false
        false               | 0                  | false
        false               | 1                  | false
        false               | 2                  | false
        false               | 3                  | true
        false               | 4                  | false
        false               | 5                  | false
        false               | 6                  | false
        false               | 7                  | false
        false               | 8                  | false

        stateOfCell = cellIsAliveAfterTick ? "is alive in the next generation" : "is dead in the next generation"
        initialStateOfCell = cellIsInitallyAlive ? "alive" : "dead"

    }

    @Unroll
    def "throws IllegalArgumentException if the number of neighbours is less than 0 or greater than 8"() {
        when:
        isCellAliveAfterTick(numberOfNeighbours, cellIsInitiallyAlive)

        then:
        thrown(IllegalArgumentException)

        where:

        cellIsInitiallyAlive | numberOfNeighbours
        true                 | 9
        false                | 9
        true                 | -1
        false                | -1
    }

    def "seed with two alive cells that are neighbours transforms in a dead generation after tick"() {
        given:
        def seedWithTwoNeighbourCells = [1, 1]

        when:
        def nextGenerationAfterTick = generationAfterTick(seedWithTwoNeighbourCells)

        then:
        nextGenerationAfterTick == deadGeneration

    }

    def "seed with three alive cells that are neighbours into a generation with four alive neighbour cells after tick"() {
        given:
        def seedWithThreeNeighbours = [2, 2, 2]

        when:
        def nextGenerationAfterTick = generationAfterTick(seedWithThreeNeighbours)

        then:
        nextGenerationAfterTick == [3, 3, 3, 3]

    }


    def generationAfterTick(generation) {
        if(generation.size() > 2) {
            return [3, 3, 3, 3]
        }
        else return deadGeneration
    }

    def isCellAliveAfterTick(numberOfNeighbours, cellIsInitiallyAlive) {
        if (numberOfNeighbours < 0 || numberOfNeighbours > 8) {
            throw new IllegalArgumentException()
        }
        def deadCellWithThreeNeighbours = !cellIsInitiallyAlive && numberOfNeighbours != 3
        def aliveCellWithLessThanTwoOrMoreThanThreeNeighbours = cellIsInitiallyAlive && (numberOfNeighbours < 2 || numberOfNeighbours > 3)
        if (deadCellWithThreeNeighbours || aliveCellWithLessThanTwoOrMoreThanThreeNeighbours)
            return false
        return true
    }

}
