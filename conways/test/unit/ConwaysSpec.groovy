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

    def tick(numberOfNeighbors) {
        return numberOfNeighbors
    }
}
