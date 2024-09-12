package hello.itemservice.repository.jdbctemplate;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *
 * SimpleJdbcInsert 기능 사용
 *
 */
@Slf4j
public class JdbcTemplateItemRepositoryV3 implements ItemRepository {

    //private final JdbcTemplate template;

    private final NamedParameterJdbcTemplate template;

    private final SimpleJdbcInsert jdbcInsert;



    public JdbcTemplateItemRepositoryV3(DataSource dataSource) {

        this.template = new NamedParameterJdbcTemplate(dataSource);

         this.jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("item")
                 .usingGeneratedKeyColumns("id");
                 //.usingColumns("item_name","price","quantity");  이부분은 생략 가능
    }

    @Override
    public Item save(Item item) {
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(item);

        Number key = jdbcInsert.executeAndReturnKey(param);
        item.setId(key.longValue());
        return item;

        // 로그를 보면 INSERT INTO item (ITEM_NAME, PRICE, QUANTITY) VALUES(?, ?, ?) 가 실행 됨을 볼수있다



        // 테스트 케이스에서 traction 중인데 jdbcInsert 든 jdbcTemplate 든 모두 트랜잭션매니저의 동기화 매니저에있는 커넥션을 갖다 쓴다


    }

    @Override
    public void update(Long itemId, ItemUpdateDto updateParam) {
        String sql="update item set item_name=:itemName,price=:price,quantity=:quantity where id=:id";

        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("itemName", updateParam.getItemName())
                .addValue("price", updateParam.getPrice())
                .addValue("quantity", updateParam.getQuantity())
                .addValue("id",itemId); //  BeanPropertySqlParameterSource가 좋은데 왜 안썻냐 ?
                // 그 이유는 이경우에는 Dto 로 전달 받는데 여기선 필드값이 id 가 없기때문에 MapSqlParameterSource 를 사용해서 add 해야함

        template.update(sql,param);



    }

    @Override
    public Optional<Item> findById(Long id) {
        String sql="select id,item_name,price,quantity  from item where id=:id";



        try{
            Map<String, Object> param = Map.of("id", id);

            Item item = template.queryForObject(sql, param, itemRowMapper());
            return Optional.of(item);
        }
        catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }



    }

    private RowMapper<Item> itemRowMapper() {

        //  이상한 람다식을 안쓰는 대신 이걸로 db 에 저장된 걸 자바 객체로 변환할수 있다
        return BeanPropertyRowMapper.newInstance(Item.class);

        // 그런데 여기서 궁금점 db는 언더스코어 규약을 사용함 item_name 근데 자바객체는 camel 규약으로 itemName 을 사용함
        //그래서 이거 변환정도는 알아서 처리해서 만들어준다

        // 즉 select item_name 으로 사용해도 자동으로 setItemName() 을 통해 값을 넣어 자바객체를 만들어준다 ( 여기선 Item 객체)


    }

    @Override
    public List<Item> findAll(ItemSearchCond cond) {
        String itemName = cond.getItemName();
        Integer maxPrice = cond.getMaxPrice();

        String sql="select id,item_name,price,quantity from item ";

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(cond);

        //동적 쿼리......................
        if (StringUtils.hasText(itemName) || maxPrice != null) {
            sql += " where";
        }
        boolean andFlag = false;

        if (StringUtils.hasText(itemName)) {
            sql += " item_name like concat('%',:itemName,'%')";

            andFlag = true;
        }
        if (maxPrice != null) {
            if (andFlag) {
                sql += " and";
            }
            sql += " price <= :maxPrice";

        }
        log.info("sql={}", sql);

        //..................... 복잡한 로직이있다 강의에서도 걍 잘 안다루고 복사




        return template.query(sql,param,itemRowMapper());



    }
}
