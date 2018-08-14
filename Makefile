CXXFLAGS= -std=c++11 -g -Iinclude

all : tests

clean :
	/bin/rm -rf bin/* tmp/* *~ src/*~ include/*~

tmp/PRNG.o : src/PRNG.cpp include/PRNG.h
	$(CXX) $(CXXFLAGS) -c -o $@ $<

tmp/testPRNG.o : src/testPRNG.cpp include/PRNG.h
	$(CXX) $(CXXFLAGS) -c -o $@ $<

bin/testPRNG : tmp/testPRNG.o tmp/PRNG.o
	$(CXX) $(CXXFLAGS) -o $@ $^ $(LDFLAGS)

tmp/PolyLog.o : src/PolyLog.cpp include/PolyLog.h
	$(CXX) $(CXXFLAGS) -c -o $@ $<

tmp/testPolyLog.o : src/testPolyLog.cpp include/PolyLog.h
	$(CXX) $(CXXFLAGS) -c -o $@ $<

bin/testPolyLog : tmp/testPolyLog.o tmp/PolyLog.o
	$(CXX) $(CXXFLAGS) -o $@ $^ $(LDFLAGS)


tmp/Stats.o : src/Stats.cpp include/Stats.h include/PRNG.h
	$(CXX) $(CXXFLAGS) -c -o $@ $<

tmp/testStats.o : src/testStats.cpp include/Stats.h
	$(CXX) $(CXXFLAGS) -c -o $@ $<

bin/testStats : tmp/testStats.o tmp/Stats.o tmp/PRNG.o
	$(CXX) $(CXXFLAGS) -o $@ $^ $(LDFLAGS)

tmp/testInsertionSort.o : src/testInsertionSort.cpp include/PRNG.h include/PolyLog.h include/Stats.h include/InsertionSort.h
	$(CXX) $(CXXFLAGS) -c -o $@ $<

bin/testInsertionSort : tmp/testInsertionSort.o tmp/Stats.o tmp/PolyLog.o tmp/PRNG.o
	$(CXX) $(CXXFLAGS) -o $@ $^ $(LDFLAGS)

tests : bin/testPRNG bin/testStats bin/testPolyLog bin/testInsertionSort
	bin/testPRNG
	bin/testStats
	bin/testPolyLog
	bin/testInsertionSort
