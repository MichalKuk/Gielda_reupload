
W g³ównym oknie s¹ listy z aktywami, inwestorami i funduszami.
Po wcisniêciu "dodaj obiekt" w nowym oknie wybiera siê z checkboxa jaki obiekt chce siê dodaæ i wype³nia siê pola, albo s¹ one same losowane. Kolejne "dodaj obiekt" tworzy obiekt (je¿eli ma w¹tek, to go uruchamia). 
Aby zobaczyæ obiekty w oknie g³ównym, trzeba u¿yæ odœwie¿ania. "Odœwie¿ aktywa" odœwie¿a równie¿ rynki wyœwietlane nad listami aktywów, przy czym w programie dzia³a tylko 1 rynek ka¿dego typu, mo¿na ich tworzyæ wiêcej, ale "aktywny" jest zawsze pierwszy z listy. Wciœniêcie "Odœwie¿ aktywa" tworzy równie¿ inwestorów i fundusze proporcjonalnie do liczby aktywów na rynku i uruchamia ich w¹tki.
"Odœwie¿ inwestorów" tylko odœwie¿a listy inwestorów i funduszy.
Po wybraniu aktywa na liœcie, na dole wyœwietl¹ siê informacje o nim. Po zaznaczeniu spó³ki mo¿na pod list¹ spó³ek wpisaæ kwotê, za któr¹ wybrana spó³ka ma odkupiæ swoje akcje.
Po wybraniu inwestora/funduszu po prawej wyœwietl¹ siê informacje o jednym z nich, wraz z list¹ posiadanych aktywów.
Przycisk "Stop" zatrzymuje dzia³anie w¹tków (do serializacji), ale nie od razu. Wykonuj¹ dalej kod metody run() do koñca, tylko ¿e nie wchodz¹ ju¿ z powrotem w pêtlê. Po wciœniêciu nale¿y poczekaæ (co najmniej 10 sekund), ¿eby mieæ pewnoœæ, ¿e wsyztskie w¹tki przesta³y dzia³aæ.
Mia³ byæ przycisk "start", ale nie chcia³ dzia³aæ.
Przycisk "zapisz" dokonuje zapisu-serializacji, "wczytaj" powinien dokonywaæ odczytu-serializacji, ale dzia³a tylko "czêœciowo" i wyskakuj¹ b³êdy.

Dzia³ania spó³ek/inwestorów/funduszy wyœwietlaj¹ siê w konsoli.
