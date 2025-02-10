package com.example.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.api.domain.FormInfo;
import com.example.api.service.CambioService;

import jakarta.validation.Valid;

@Controller
public class CambioController {

    @Autowired
    public CambioService cambioService;

    @GetMapping("/")
    public String showForm(@RequestParam(required = false) Integer err, Model model) {
        model.addAttribute("formInfo", new FormInfo());
        model.addAttribute("monedas", cambioService.obtenerMonedas());
        if (err != null) {
            if (err == 1)
                model.addAttribute("errorTxt", "error param entrada");
            else if (err == 2)
                model.addAttribute("errorTxt", "monedas iguales");
            else
                model.addAttribute("errorTxt", "error imprevisto");
        }
        return "indexView";
    }

    @PostMapping("/")
    public String showFormSubmit(
            @Valid FormInfo formInfo,
            BindingResult bindingResult,
            Model model) {

        model.addAttribute("formInfo", formInfo);
        model.addAttribute("monedas", cambioService.obtenerMonedas());
        if (bindingResult.hasErrors()) {
            return "redirect:/?err=1";
        }
        Double importeCambiado = cambioService.calcularImporteCambiado(formInfo.getMonedaOrigen(), formInfo.getMonedaDestino(), formInfo.getImporte());
        model.addAttribute("importeCambiado", importeCambiado);
        model.addAttribute("monedaOrigen", formInfo.getMonedaOrigen());
        model.addAttribute("valorOrigen", formInfo.getImporte());
        model.addAttribute("monedaDestino", formInfo.getMonedaDestino());
        return "indexView";

    }
}