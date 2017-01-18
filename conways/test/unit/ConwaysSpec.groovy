import spock.lang.Specification
import spock.lang.Unroll

class ConwaysSpec extends Specification {


    @Unroll
    def "alive cell with number of neighbors = #numberOfNeighbors #stateOfCell"() {

        when:
        def cellIsAlive = tick(numberOfNeighbors)

        then:
         expectedStateOfCell == cellIsAlive

        where:
        numberOfNeighbors | expectedStateOfCell
        0                 | false
        1                 | false
        2                 | true
        3                 | true

        stateOfCell = expectedStateOfCell ? "lives" : "dies"

    }


    def tick(numberOfNeighbors) {
        if (numberOfNeighbors < 2)
            return false
        return true
    }
}
