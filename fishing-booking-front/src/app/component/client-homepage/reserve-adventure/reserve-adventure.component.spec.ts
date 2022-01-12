import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReserveAdventureComponent } from './reserve-adventure.component';

describe('ReserveAdventureComponent', () => {
  let component: ReserveAdventureComponent;
  let fixture: ComponentFixture<ReserveAdventureComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReserveAdventureComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReserveAdventureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
