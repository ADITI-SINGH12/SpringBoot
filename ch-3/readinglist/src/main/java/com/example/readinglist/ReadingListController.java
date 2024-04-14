package com.example.readinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
public class ReadingListController {
  private ReadingListInterface readingListInterface;
    @Autowired
    public ReadingListController(ReadingListInterface readingListInterface) {
        this.readingListInterface = readingListInterface;
    }
    /* readersBooks()—Handles HTTP GET requests for /{reader} by retrieving a
    Book list from the repository (which was injected into the controller’s constructor) for the reader specified in the path. It puts the list of Book into the model
    under the key “books” and returns “readingList” as the logical name of the view
    to render the model*/
    @RequestMapping(value = "/{reader}",method = RequestMethod.GET)
    public String readerbooks(@PathVariable("reader") String reader, Model model){
     List<Book> readinglists = readingListInterface.findByReader(reader);
     if(readinglists != null){
         model.addAttribute("books",readinglists);
     }
     return "readinglists";
    }
    /*Handles HTTP POST requests for /{reader}, binding the
data in the body of the request to a Book object. This method sets the Book
object’s reader property to the reader’s name, and then saves the modified
Book via the repository’s save() method. Finally, it returns by specifying a redirect to /{reader}
(which will be handled by the other controller method).*/
    @RequestMapping(value = "/{reader}",method = RequestMethod.POST)
    public String addtoReadingList(@PathVariable("reader") String reader, Book book){
        book.setReader(reader);
        readingListInterface.save(book);
        return "redirect:/{reader}";
    }
}
