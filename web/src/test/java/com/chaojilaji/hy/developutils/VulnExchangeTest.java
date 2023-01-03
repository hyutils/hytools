package com.chaojilaji.hy.developutils;

import com.chaojilaji.hyutils.dbcore.utils.ArrayStrUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class VulnExchangeTest {


    @Test
    public void demo1() {
        String a = "482514807505816056\t513211418628065611\n" +
                "482514805769374126\t513211417646598472\n" +
                "482514805857454514\t513211418317687114\n" +
                "482514806172027326\t513211418955221324\n" +
                "482514805899397556\t513211419630504270\n" +
                "482514805635156392\t513211417952782665\n" +
                "482514806813755865\t513211420142209359\n" +
                "482514806968945119\t513211420800715089\n" +
                "482514807484844535\t513211427008285027\n" +
                "482514807296100846\t513211428027500902\n" +
                "482514807359015409\t513211428350462311\n" +
                "482514806054586809\t513211423485069657\n" +
                "482514806151055805\t513211424156158299\n" +
                "482514806109112763\t513211424529451356\n" +
                "482514806025226680\t513211425498335583\n" +
                "482514805974895030\t513211428686006632\n" +
                "482514806838921690\t513211423820613978\n" +
                "482514806943779294\t513211425196345694\n" +
                "482514807212214762\t513211426177812833\n" +
                "482514807254157804\t513211426672740706\n" +
                "482514807170271720\t513211428950247785\n" +
                "482514807082191332\t513211425871628640\n" +
                "482514807442901493\t513211431068371310\n" +
                "482514807547759098\t513211433551399285\n" +
                "482514807463873014\t513211433891137910\n" +
                "482514807526787577\t513211438249019778\n" +
                "482514805878426035\t513211431374555503\n" +
                "482514805836482993\t513211434558032248\n" +
                "482514805794539951\t513211437074614655\n" +
                "482514806763424215\t513211431777208688\n" +
                "482514806864087515\t513211434906159481\n" +
                "482514807191243241\t513211432045644145\n" +
                "482514807040248290\t513211432913865075\n" +
                "482514807061219811\t513211435229120890\n" +
                "482514805656127913\t513211436239948157\n" +
                "482514805698070955\t513211433220049268\n" +
                "482514805681293738\t513211434256042359\n" +
                "482514807019276769\t513211432414742898\n" +
                "482514806994110944\t513211435900209532\n" +
                "482514807421929972\t513211439456979333\n" +
                "482514807379986930\t513211444792134035\n" +
                "482514807275129325\t513211445161232788\n" +
                "482514805941340597\t513211440966928777\n" +
                "482514806000060855\t513211445802961302\n" +
                "482514806788590040\t513211440430057864\n" +
                "482514806738258390\t513211443147966863\n" +
                "482514807128328678\t513211441302473098\n" +
                "482514807233186283\t513211443449956752\n" +
                "482514807149300199\t513211443953273233\n" +
                "482514807107357157\t513211440128067975\n" +
                "482514805719042476\t513211444293011858\n" +
                "482514806415296969\t513211416140843332\n" +
                "482514806511765965\t513211416438638917\n" +
                "482514805500938662\t513211416774183238\n" +
                "482514806620817873\t513211417109727559\n" +
                "482514806352382406\t513211419324320077\n" +
                "482514806461434315\t513211424860801373\n" +
                "482514806394325448\t513211427343829348\n" +
                "482514806566291919\t513211427717122405\n" +
                "482514806436268490\t513211435564665211\n" +
                "482514806373353927\t513211436772624766\n" +
                "482514806486600140\t513211437410158976\n" +
                "482514806595652048\t513211437745703297\n" +
                "482514806671149523\t513211438584564099\n" +
                "482514806692121044\t513211438920108420\n" +
                "482514806327216581\t513211439758969222\n" +
                "482514805605796263\t513211441617045899\n" +
                "482514806541126094\t513211442107779468\n" +
                "482514806645983698\t513211442510432653\n" +
                "482514806717286869\t513211442783062414\n" +
                "513211446843148697\t513211446843148697\n" +
                "482514807317072367\t513211429726194027\n" +
                "482514807338043888\t513211430569249133\n" +
                "482514807400958451\t513211446515992984\n" +
                "482514806083946938\t513211420465170768\n" +
                "482514806306245060\t513211421136259410\n" +
                "482514806260107714\t513211421471803731\n" +
                "482514806239136193\t513211421807348052\n" +
                "482514805815511472\t513211422310664533\n" +
                "482514806213970368\t513211422562322774\n" +
                "482514806130084284\t513211422813981015\n" +
                "482514806197193151\t513211423149525336\n" +
                "482514806281079235\t513211429227071850\n" +
                "482514805744208301\t513211430195956076\n" +
                "482514806889253340\t513211445467416981\n" +
                "482514806918613469\t513211446134311319\n";
        Map<String, String> idChanges = new HashMap<>();
        ArrayStrUtil.str2Array(a, "\n").forEach(b -> {
            if (StringUtils.hasText(b)) {
                idChanges.put(b.split("\t")[0], b.split("\t")[1]);
            }
        });
        String content = FileUtils.getFileContent("D:\\2022项目\\记忆宫殿\\部署包\\1123部署准备\\漏洞转换\\vuln原始.txt", false);
        for (String key : idChanges.keySet()) {
            String val = idChanges.get(key);
            System.out.println(key + "是否会被替换：" + content.contains(key) + " 为" + val + "!");
            content = content.replaceAll(key, val);
        }
        FileUtils.createFileWithFullPath("D:\\2022项目\\记忆宫殿\\部署包\\1123部署准备\\漏洞转换\\vuln新的.txt", content);
    }



    private static int a = 0;

    public void demo(){
        a = 0;
        a++;
    }




    @Test
    public void demo123() {
//        Double deg = Math.PI / 180;
//        Integer rangeDeg = 60;
//        Integer startDeg = 30 + 180;
//        List<String> xy1 = new ArrayList<>();
//        for (int i = 0; i < 6; i++) {
//            Double x = 40 * Math.cos((startDeg - i * rangeDeg) * deg);
//            Double y = 40 * Math.sin((startDeg - i * rangeDeg) * deg);
//            xy1.add(x + "," + y);
//        }
//        List<String> xy2 = new ArrayList<>();
//        for (int i = 0; i < 6; i++) {
//            Double x = 80 * Math.cos((startDeg - i * rangeDeg) * deg);
//            Double y = 80 * Math.sin((startDeg - i * rangeDeg) * deg);
//            xy2.add(x + "," + y);
//        }
        for (int i=0;i<10;i++){
            demo();
            System.out.println(a);
        }
    }
}
