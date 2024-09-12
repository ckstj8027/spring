package hello.itemservice.repository.jdbctemplate;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/** NamedParameterJdbcTemplate
 * param 전달 방식은
 * 1 Map 을써서 전달도 되고
 * 2 SqlParameterSource 라는 인터페이스 를 사용해도됨 밑에는 구현체
 * - BeanPropertySqlParameterSource  // 이친구가 제일좋음
 * - MapSqlParameterSource  // map 과 유사
 *
 *
 *
 *
 * BeanPropertyRowMapper 사용가능
 */
@Slf4j
public class JdbcTemplateItemRepositoryV2 implements ItemRepository {

    //private final JdbcTemplate template;

    private final NamedParameterJdbcTemplate template;



    public JdbcTemplateItemRepositoryV2(DataSource dataSource) {

        this.template = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Item save(Item item) {
//        1SQL 쿼리 실행:
//          template.update(sql, param, keyHolder)는 sql 쿼리를 데이터베이스에 실행합니다.
//          이 쿼리는 데이터베이스의 item 테이블에 새로운 레코드를 삽입합니다.
//        2파라미터 바인딩:
//          param 객체는 item 객체의 속성을 SQL 쿼리의 파라미터에 바인딩합니다.
//          예를 들어, item_name, price, quantity 값이 쿼리에 적용됩니다.
//        3 자동 생성된 키 저장:
//          데이터베이스가 자동으로 생성한 키(예: AUTO_INCREMENT 필드의 값)가 keyHolder에 저장됩니다.
//          keyHolder는 이 값을 추출할 수 있는 방법을 제공합니다.

        String sql="insert into item(item_name,price,quantity) values(:itemName,:price,:quantity)";

        KeyHolder keyHolder=new GeneratedKeyHolder();

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(item);
        log.info(" 빈 프로터티 에스큐엘 파라미터 소스 파람 : {}",param);

        // item 을 읽어 들여서 파라미터 객체 를 생성한다

        template.update(sql,param,keyHolder);


        long key=keyHolder.getKey().longValue();
        item.setId(key);
        return item;

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
