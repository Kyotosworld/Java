#!/bin/sh

echo "Script $0 : $# arguments passed"

for i in `seq 1 $#`; do
    echo "    public function set$1(\$val) {
        if (\$val) {
            \$this->_$1 = \$val;
        } else {
            echo 'Erreur: la propriete \'$1\' n'a pas ete modifiee'
        }
    }"
    shift
done
