
W g��wnym oknie s� listy z aktywami, inwestorami i funduszami.
Po wcisni�ciu "dodaj obiekt" w nowym oknie wybiera si� z checkboxa jaki obiekt chce si� doda� i wype�nia si� pola, albo s� one same losowane. Kolejne "dodaj obiekt" tworzy obiekt (je�eli ma w�tek, to go uruchamia). 
Aby zobaczy� obiekty w oknie g��wnym, trzeba u�y� od�wie�ania. "Od�wie� aktywa" od�wie�a r�wnie� rynki wy�wietlane nad listami aktyw�w, przy czym w programie dzia�a tylko 1 rynek ka�dego typu, mo�na ich tworzy� wi�cej, ale "aktywny" jest zawsze pierwszy z listy. Wci�ni�cie "Od�wie� aktywa" tworzy r�wnie� inwestor�w i fundusze proporcjonalnie do liczby aktyw�w na rynku i uruchamia ich w�tki.
"Od�wie� inwestor�w" tylko od�wie�a listy inwestor�w i funduszy.
Po wybraniu aktywa na li�cie, na dole wy�wietl� si� informacje o nim. Po zaznaczeniu sp�ki mo�na pod list� sp�ek wpisa� kwot�, za kt�r� wybrana sp�ka ma odkupi� swoje akcje.
Po wybraniu inwestora/funduszu po prawej wy�wietl� si� informacje o jednym z nich, wraz z list� posiadanych aktyw�w.
Przycisk "Stop" zatrzymuje dzia�anie w�tk�w (do serializacji), ale nie od razu. Wykonuj� dalej kod metody run() do ko�ca, tylko �e nie wchodz� ju� z powrotem w p�tl�. Po wci�ni�ciu nale�y poczeka� (co najmniej 10 sekund), �eby mie� pewno��, �e wsyztskie w�tki przesta�y dzia�a�.
Mia� by� przycisk "start", ale nie chcia� dzia�a�.
Przycisk "zapisz" dokonuje zapisu-serializacji, "wczytaj" powinien dokonywa� odczytu-serializacji, ale dzia�a tylko "cz�ciowo" i wyskakuj� b��dy.

Dzia�ania sp�ek/inwestor�w/funduszy wy�wietlaj� si� w konsoli.
