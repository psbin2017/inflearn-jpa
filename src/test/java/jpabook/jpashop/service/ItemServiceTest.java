package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Album;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Movie;
import jpabook.jpashop.global.exception.NotEnoughStockException;
import jpabook.jpashop.item.ItemBuilder;
import jpabook.jpashop.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {

    @Autowired private ItemService itemService;
    @Autowired private ItemRepository itemRepository;

    @Test
    public void save_book() throws Exception {
        // given
        Book book = ItemBuilder.book();

        // when
        itemService.saveItem(book);
        Book findBook = (Book) itemService.findOne(book.getId());

        // then
        assertThat(findBook.getId()).isEqualTo(book.getId());
        assertThat(findBook.getName()).isEqualTo(book.getName());
        assertThat(findBook.getPrice()).isEqualTo(book.getPrice());
        assertThat(findBook.getStockQuantity()).isEqualTo(book.getStockQuantity());
        assertThat(findBook.getAuthor()).isEqualTo(book.getAuthor());
        assertThat(findBook.getIsbn()).isEqualTo(book.getIsbn());
    }

    @Test
    public void save_movie() throws Exception {
        // given
        Movie movie = ItemBuilder.movie();

        // when
        itemService.saveItem(movie);
        Movie findMovie = (Movie) itemService.findOne(movie.getId());

        // then
        assertThat(findMovie.getId()).isEqualTo(movie.getId());
        assertThat(findMovie.getName()).isEqualTo(movie.getName());
        assertThat(findMovie.getPrice()).isEqualTo(movie.getPrice());
        assertThat(findMovie.getStockQuantity()).isEqualTo(movie.getStockQuantity());
        assertThat(findMovie.getActor()).isEqualTo(movie.getActor());
        assertThat(findMovie.getDirector()).isEqualTo(movie.getDirector());
    }

    @Test
    public void save_album() throws Exception {
        // given
        Album album = ItemBuilder.album();

        // when
        itemService.saveItem(album);
        Album findAlbum = (Album) itemService.findOne(album.getId());

        // then
        assertThat(findAlbum.getId()).isEqualTo(album.getId());
        assertThat(findAlbum.getName()).isEqualTo(album.getName());
        assertThat(findAlbum.getPrice()).isEqualTo(album.getPrice());
        assertThat(findAlbum.getStockQuantity()).isEqualTo(album.getStockQuantity());
        assertThat(findAlbum.getArtist()).isEqualTo(album.getArtist());
        assertThat(findAlbum.getEtc()).isEqualTo(album.getEtc());
    }

    @Test
    public void savedChange_quantityAdded() throws Exception {
        // given
        Album album = ItemBuilder.album();

        // when
        itemService.saveItem(album);
        int originQuantity = album.getStockQuantity();

        album.addStock(100);
        itemService.saveItem(album);

        // then
        assertThat(originQuantity).isNotEqualTo(album.getStockQuantity());
    }

    @Test(expected = NotEnoughStockException.class)
    public void quantityExceeded_notEnoughStockException() throws Exception {
        // given
        Album album = ItemBuilder.album();

        // when
        int originQuantity = album.getStockQuantity();

        // then
        // 현재 전 재고 수량보다 하나 더 뺌
        album.removeStock(originQuantity + 1);
    }

}