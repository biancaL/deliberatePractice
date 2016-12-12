import spock.lang.Specification

class ConwaysSpec extends Specification {

    def "alive cell with no neighbors dies"() {
        given:
        def numberOfNeighbors = 0

        when:
        def numberOfAliveCells = tick(numberOfNeighbors)

        then:
        numberOfAliveCells == 0
    }

    def "alive cell with one neighbor dies"() {
        given:
        def numberOfNeighbors = 1

        when:
        def numberOfAliveCells = tick(numberOfNeighbors)

        then:
        numberOfAliveCells == 0
    }

    def tick(numberOfNeighbors) {
        if(numberOfNeighbors < 2)
            return 0
    }
}
