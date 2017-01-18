import spock.lang.Specification
import spock.lang.Unroll

class ConwaysSpec extends Specification {


    @Unroll
    def "alive cell with #numberOfNeighbours neighbour(s) #stateOfCell"() {

        when:
        def cellIsAlive = tick(numberOfNeighbours)

        then:
        expectedStateOfCell == cellIsAlive

        where:
        numberOfNeighbours | expectedStateOfCell
        0                  | false
        1                  | false
        2                  | true
        3                  | true
        4                  | false

        stateOfCell = expectedStateOfCell ? "lives" : "dies"

    }


    def tick(numberOfNeighbours) {
        if (numberOfNeighbours < 2 || numberOfNeighbours > 3)
            return false
        return true
    }
}
