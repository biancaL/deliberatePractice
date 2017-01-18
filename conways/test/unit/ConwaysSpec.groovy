import spock.lang.Specification
import spock.lang.Unroll

class ConwaysSpec extends Specification {


    @Unroll
    def "#initialStateOfCell cell with #numberOfNeighbours neighbour(s) #stateOfCell"() {

        when:
        def cellIsAlive = tick(numberOfNeighbours, cellIsInitallyAlive)

        then:
        expectedStateOfCell == cellIsAlive

        where:
        cellIsInitallyAlive | numberOfNeighbours | expectedStateOfCell
        true                | 0                  | false
        true                | 1                  | false
        true                | 2                  | true
        true                | 3                  | true
        true                | 4                  | false
        false               | 0                  | false

        stateOfCell = expectedStateOfCell ? "lives" : "dies"
        initialStateOfCell = cellIsInitallyAlive ? "alive" : "dead"

    }


    def tick(numberOfNeighbours, cellIsInitiallyAlive) {
        if (!cellIsInitiallyAlive || (numberOfNeighbours < 2 || numberOfNeighbours > 3))
            return false
        return true
    }
}
