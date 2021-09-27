package com.example.kuniyakino.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.kuniyakino.DBHelper;
import com.example.kuniyakino.R;
import com.example.kuniyakino.adapter.BookAdapter;
import com.example.kuniyakino.model.Book;

import java.util.List;

public class ProductFragment extends Fragment {

    RecyclerView bookItemRecycler;
    BookAdapter bookAdapter;
    List<Book> booksList;
    DBHelper dbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        dbHelper = new DBHelper(getContext());

        if (dbHelper.getAllProduct().isEmpty()) {
            dbHelper.insertProductData(1, "ambition", "Seneca Fauls", "Peril", "15.95", "American society struggles to recover from an eruption at the Yellowstone super caldera that left millions dead and forced those alive to migrate east to avoid starvation when crops failed. The Reclamation Party, lead by charismatic Senator Jaxson Blake, promises to restore prosperity. When reporter Jasmine Blare uncovers a government program that removes undesirables from the population, she must find a way to stop the slaughter, even if it destroys Jaxson, the only man she has ever loved.", 230, 79, R.drawable.ambition);
            dbHelper.insertProductData(2, "The Marriage Auction", "Audrey Carlan", "Zakir Hossain", "16.00", "What would you do for 3 million dollars? Four women enter into a clandestine auction to be married to the highest bidders. Entangled in a high-stakes game of money, lust, power, and the hope for absolution, these women become a sisterhood unlike any other. Once chosen by a man she's never met and agrees to marry sight unseen... there is no going back. Secrets, desires, fiery couplings and drama are all part of the deal when you enter into The Marriage Auction. You may now kiss the bride...", 453, 101, R.drawable.marriage);
            dbHelper.insertProductData(3, "Black Ice: A Thriller ", "Brad Thor", "Atria/Emily Bestler Books", "14.99", "Black Ice is another instant classic from Brad Thor, who continues to push the envelope in ways that few before him have ever dared.", 336, 120, R.drawable.black);
            dbHelper.insertProductData(4, "Golden Girl", "Elin Hilderbrand", "Little, Brown and Company", "10.93", "On a perfect June day, Vivian Howe, author of thirteen beach novels and mother of three nearly grown children, is killed in a hit-and-run car accident while jogging near her home on Nantucket. She ascends to the Beyond where she's assigned to a Person named Martha, who allows Vivi to watch what happens below for one last summer. Vivi also is granted three “nudges” to change the outcome of events on earth, and with her daughter Willa on her third miscarriage, Carson partying until all hours, and Leo currently “off again” with his high-maintenance girlfriend, she’ll have to think carefully where to use them.", 385, 107, R.drawable.goldengirl);
            dbHelper.insertProductData(5, "The Invisible Life of Addie LaRue ", "V. E. Schwab", "Titan Books", "14.99", "When Addie La Rue makes a pact with the devil, she trades her soul for immortality. But there's always a price - the devil takes away her place in the world, cursing her to be forgotten by everyone.", 517, 205, R.drawable.larue);
            dbHelper.insertProductData(6, "The Midnight Library", "Matt Haig", "Canongate Books", "7.30", "When the death of her cat proves the final straw, Nora decides to check out on life, and finds herself at the Midnight Library. Even death was something Nora couldn't do properly, it seemed.\" But each book at this library tells the story of a life she could have had. Part It’s a Wonderful Life, part Oona Out of Order, this charming, funny, inventive novel is about regret, the choices we make, and taking the bitter with the sweet. ", 300, 507, R.drawable.midnight);
            dbHelper.insertProductData(7, "The Four Winds", "Kristin Hannah", "Macmillan", "13.77", "Texas, 1934. Elsa Martinelli had finally found the life she’d yearned for. A family, a home and a livelihood on a farm on the Great Plains. But when drought threatens all she and her community hold dear, Elsa’s world is shattered to the winds.", 65, 205, R.drawable.wind);
            dbHelper.insertProductData(8, "A Wizard of Earthsea", "Ursula K. Le Guin", "Clarion Books", "6.45", "Originally published in 1968, Ursula K. Le Guin’s A Wizard of Earthsea marks the first of the six now beloved Earthsea titles. Ged was the greatest sorcerer in Earthsea, but in his youth he was the reckless Sparrowhawk. In his hunger for power and knowledge, he tampered with long-held secrets and loosed a terrible shadow upon the world. This is the tumultuous tale of his testing, how he mastered the mighty words of power, tamed an ancient dragon, and crossed death's threshold to restore the balance.", 210, 50, R.drawable.eagle);
            dbHelper.insertProductData(9, "The Missing Sister", "Lucinda Riley", "Macmillan", "9.99", "From the Sunday Times number one bestselling author Lucinda Riley, The Missing Sister is the seventh instalment in the multimillion-copy epic series The Seven Sisters.", 651, 50, R.drawable.sister);
            dbHelper.insertProductData(10, "The Last Thing He Told Me", "Laura Dave", "Viper", "8.91", "Before Owen Michaels disappears, he manages to smuggle a note to his new wife, Hannah: protect her. Hannah knows exactly who Owen needs her to protect - his sixteen-year-old daughter, Bailey, who lost her mother tragically as a child. And who wants absolutely nothing to do with her new stepmother.", 319, 406, R.drawable.last);
            dbHelper.insertProductData(11, "The Terminal List", "Jack Carr", "Atria/Emily Bestler Books", "12.99", "On his last combat deployment, Lieutenant Commander James Reece’s entire team was killed in a catastrophic ambush. But when those dearest to him are murdered on the day of his homecoming, Reece discovers that this was not an act of war by a foreign enemy but a conspiracy that runs to the highest levels of government.", 434, 504, R.drawable.terminate);
            dbHelper.insertProductData(12, "True Believer", "Jack Carr", "Atria/Emily Bestler Books", "7.11", "When a string of horrific terrorist attacks plagues the Western world during the holiday season, the broader markets fall into a tailspin. The attacks are being coordinated by a shadowy former Iraqi commando who has disappeared into Europe’s underground. The United States government has an asset who can turn the Iraqi against his masters: James Reece, the most-wanted domestic terrorist alive.", 497, 740, R.drawable.truebeliever);
            dbHelper.insertProductData(13, "Three Sips of Gin:", "Timothy G. Bax", "Helion and Company", "4.99", "There is nothing that terrorized Russian and Chinese-backed guerillas fighting Rhodesia's bush war in the 1970s more than the famed Selous Scouts. The very name of the unit struck fear into the very heart and soul of even the most battle-hardened guerillas. Too afraid to even whisper the name amongst themselves, they referred to soldiers of the unit simply as Skuzapu, or pickpockets. It was not for nothing that history has recorded the Selous Scouts Regiment as being one of the deadliest and most effective killing machines in modern counter-insurgency warfare.", 679, 976, R.drawable.three);
            dbHelper.insertProductData(14, "Bush War Operator", "Andrew Balaam", "Helion and Company", "4.99", "Anyone living in Rhodesia during the 1960s and 1970s would have had a father, husband, brother or son called up in the defense of the war-torn, landlocked little country. A few of these brave men would have been members of the elite and secretive unit that struck terror into the hearts of the ZANLA and ZIPRA guerrillas infiltrating the country at that time - the Selous Scouts. These men were highly trained and disciplined, with skills to rival the SAS, Navy Seals and the US Marines, although their dress and appearance were wildly unconventional: civilian clothing with blackened, hairy faces to resemble the very people they were fighting against.", 486, 684, R.drawable.bush);
            dbHelper.insertProductData(15, "Spider Zero Seven", "Mike Borlace", " Matador", "2.99", "Helicopters were a vital component of the small Rhodesian Defence Force and as part of special forces, Borlace and his fellow aircrew soon became key weapons in the counterinsurgency operations. Adopting new flexible tactics and blending stealth with courage, they carried the fight by air to the heart of the enemy, establishing a fearsome reputation. In this vivid history, Borlace chronicles the story of airmen, soldiers and leading figures such as Joshua Nkomo and Robert Mugabe’s communist backed guerillas from the perspective of a professional officer at the sharp end.", 214, 147, R.drawable.spider);
            dbHelper.insertProductData(16, "The Nightingale", "Kristin Hannah", "Pan", "8.25", "This story is about what it was like to be a woman during World War II when women’s stories were all too often forgotten or overlooked . . . Vianne and Isabelle Mauriac are two sisters, separated by years and experience, by ideals and passion and circumstance, each embarking on her own dangerous path towards survival, love and freedom in war-torn France.", 465, 504, R.drawable.nightangle);
            dbHelper.insertProductData(17, "Firefly Lane", "Kristin Hannah", "Pan", "9.99", "It is 1974 and the summer of love is drawing to a close. Kate Mularkey has accepted her place at the bottom of the secondary school social food chain. Then, to her amazement, Tully Hart – the girl all the boys want to know – moves in across the street and wants to be her best friend. Tully and Kate became inseparable and by summer’s end they vow that their friendship will last forever. ", 497, 420, R.drawable.firefly);
            dbHelper.insertProductData(18, "Between Sisters", "Kristin Hannah", "Pan", "12.43", "We all make mistakes, but for Meghann Dontess the terrible choice she made some years ago cost her everything, including the love of her sister, Claire. Meghann is now a highly successful attorney, and has put all thoughts of love completely behind her – until she meets the one man who believes he can change her mind.", 499, 102, R.drawable.bsister);
            dbHelper.insertProductData(19, "Night Road", "Kristin Hannah", "Pan", "12.38", "exi and Mia are inseparable from the moment they start high school. Different in so many ways – Lexi is an orphan and lives with her aunt on a trailer park, while Mia is a golden girl blessed with a loving family, and a beautiful home. Yet they recognize something in each other which sets them apart from the crowd, and Mia comes to rely heavily on Lexi’s steadfast friendship.", 396, 150, R.drawable.nightoad);
            dbHelper.insertProductData(20, "Count the Ways", "Joyce Maynard ", "William Morrow", "14.99", "In her most ambitious novel to date, New York Times bestselling author Joyce Maynard returns to the themes that are the hallmarks of her most acclaimed work in a mesmerizing story of a family—from the hopeful early days of young marriage to parenthood, divorce, and the costly aftermath that ripples through all their lives", 464, 541, R.drawable.count);
            dbHelper.insertProductData(21, "Hidden Sins", "Selena Montgomery", "HarperCollins e-books", "6.99", "Mara Reed has been stirring up trouble since she was eighteen—running scams, living on the edge, always on the run. Now, when two thugs are after her with murder on their minds, she’s forced into hiding in her small Texas hometown. But as she’s cornered in an alley, only seconds from death, an unexpected rescuer comes to her aid…", 384, 568, R.drawable.hidden);
            dbHelper.insertProductData(22, "Secrets and Lies", "Selena Montgomery", "HarperCollins e-books", "6.99", "She may be smart and beautiful, but she’s also standing between him and a very lucrative item he needs to “recover” in this African-American love story that blends passion with action that is sure to thrill romance readers.", 381, 183, R.drawable.secret);
            dbHelper.insertProductData(23, "Reckless ", "Selena Montgomery", "HarperCollins e-books", "4.99", "Kell Jameson has the life she’s always dreamed about. A partner at a prestigious Atlanta law firm that represents famous—if guilty—clients, she’s far from her days as a lonely orphan in rural Georgia. But one frantic phone call will bring her back to the place she’s spent years trying to escape. The head of her childhood orphanage has been accused of murder, and Kell is her only hope for freedom.", 385, 417, R.drawable.reckless);
            dbHelper.insertProductData(24, "Never Tell", "Selena Montgomery", "St. Martin's Griffin", "7.65", "Criminal psychologist Dr. Erin Abbott wants nothing more than to live a quiet life. That means no danger, no intrigue—and absolutely no romance. But when Erin suspects a serial killer is roaming New Orleans, her investigation throws her straight into the arms of the only man who can help her.", 320, 302, R.drawable.never);
            dbHelper.insertProductData(25, "Deception", "Selena Montgomery", "HarperCollins e-books", "4.99", "Playing the odds has always been Fin Borders’ forte. As a professional poker player, she knows when to get out to keep from losing everything. But an innocent woman has been accused of murder, and to help, Fin will have to go back to the small southern town of her birth. It’s a place she’s been running from her entire life, a place of violence, where she got by with nothing more than her wits. Returning to Hallden, Georgia, means facing the ghosts of a brutal crime that Fin will never forget.", 271, 721, R.drawable.decep);
        }
        booksList = dbHelper.getAllProduct();
        setProductItemRecycler(booksList);

        SearchView searchView = getView().findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                bookAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    private void setProductItemRecycler(List<Book> booksList) {
        bookItemRecycler = (RecyclerView)getView().findViewById(R.id.bookRecycler);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        bookItemRecycler.setLayoutManager(layoutManager);
        bookAdapter = new BookAdapter(getContext(), booksList);
        bookItemRecycler.setAdapter(bookAdapter);
    }
}