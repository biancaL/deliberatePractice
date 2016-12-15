import spock.lang.Specification

class ConwaysSpec extends Specification {

    def "alive cell with no neighbors dies"() {
        given:
        def numberOfNeighbors = 0

        when:
        def cellIsAlive = tick(numberOfNeighbors)

        then:
        cellIsAlive == false
    }

    def "alive cell with one neighbor dies"() {
        given:
        def numberOfNeighbors = 1

        when:
        def cellIsAlive = tick(numberOfNeighbors)

        then:
        cellIsAlive == false
    }

    def "alive cell with two neighbors lives"() {
        given:
        def numberOfNeighbors = 2

        when:
        def cellIsAlive = tick(numberOfNeighbors)

        then:
        cellIsAlive == true
    }

    def "alive cell with three neighbors lives"() {
        given:
        def numberOfNeighbors = 3

        when:
        def cellIsAlive = tick(numberOfNeighbors)

        then:
        cellIsAlive == true
    }

    def tick(numberOfNeighbors) {
        if(numberOfNeighbors < 2)
            return false
        return true
    }
}
